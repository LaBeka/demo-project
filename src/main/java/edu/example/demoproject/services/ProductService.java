package edu.example.demoproject.services;

import edu.example.demoproject.dtos.product.ProductSearchDto;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.entities.Product;
import edu.example.demoproject.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EntityManager enManager;

    public boolean isAllowedAddNewProd(ProductCreateDto dto){
        Optional<Product> findIdenticalProduct = productRepository.findByNameAndBrand(dto.getName(), dto.getBrand());
        return findIdenticalProduct.isPresent();
    }

    public Optional<Product> saveProduct(ProductCreateDto dto) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        MultipartFile photo = dto.getImage();

        String newName = dtf.format(LocalDateTime.now()) +
                "-" + photo.getOriginalFilename();

        Path mainDirectoryForProdImages = Paths.get("storage");
        Files.createDirectories(mainDirectoryForProdImages);
        Path newFilePath = mainDirectoryForProdImages.resolve(newName); //ad this file name
        photo.transferTo(newFilePath);

        Product product = Product.builder()
                .name(dto.getName())
                .image(newName)
                .description(dto.getDescription())
                .initialPrice(dto.getInitialPrice())
                .storageQty(dto.getStorageQty())
                .discount(dto.getDiscount())
                .newProduct(true)
                .brand(dto.getBrand())
                .category(dto.getCategory())
                .build();

        Optional<Product> saved = Optional.of(productRepository.save(product));
        return saved;
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    private Long getTotalCount(CriteriaBuilder criteriaBuilder, Predicate predicate){
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(criteriaBuilder.count(root));

        criteriaQuery.where(predicate);
        TypedQuery<Long> query = enManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
    private Expression<String> getLoweredWord(String word, CriteriaBuilder criteriaBuilder){
        Expression<String> theWord = criteriaBuilder.literal("%" + word + "%");
       return criteriaBuilder.lower(theWord);
    }

    private Expression<String> getLoweredField(javax.persistence.criteria.Path<String> field, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lower(field);
    }

    private PageImpl<Product> returnSearch(Pageable pageable, CriteriaBuilder criteriaBuilder, CriteriaQuery<Product> criteriaQuery,
                                           Root<Product> from, CriteriaQuery<Product> select, Predicate predicates) {
        criteriaQuery.orderBy(criteriaBuilder.desc(from.get("currentPrice")));
        TypedQuery<Product> typedQuery = enManager.createQuery(select);

        typedQuery.setFirstResult(Math.toIntExact(pageable.getOffset()));
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Product> resultList = typedQuery.getResultList();
        return new PageImpl<Product>(resultList, pageable, getTotalCount(criteriaBuilder, predicates));
    }

    public PageImpl<Product> rawSearch(ProductSearchDto dto, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = enManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery =
                criteriaBuilder.createQuery(Product.class);

        Root<Product> from = criteriaQuery.from(Product.class);
        CriteriaQuery<Product> select = criteriaQuery.select(from);
        Predicate predicate = null;

        if (dto.getBrand() != null) {
            predicate = criteriaBuilder.equal(from.get("brand"), dto.getBrand());
        }

        if (dto.isNewProduct()) {
            predicate = criteriaBuilder.equal(from.get("newProduct"), dto.isNewProduct());
        }
        if (dto.getCategory() != null) {
            predicate = criteriaBuilder.equal(from.get("category"), dto.getCategory());
        }

        if (dto.isLoweredPrice()) {
            predicate= criteriaBuilder.between(from.get("discount"), 1, 99);
        }

        criteriaQuery.where(predicate);
        return returnSearch(pageable, criteriaBuilder, criteriaQuery, from, select, null);
    }
    public PageImpl<Product> searchInWord(String word, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = enManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = criteriaQuery.from(Product.class);
        CriteriaQuery<Product> select = criteriaQuery.select(from);

        Expression<String> lowSearchWord = getLoweredWord(word, criteriaBuilder);
        Expression<String> lowFieldName = getLoweredField(from.get("name"), criteriaBuilder);
        Predicate predicateName = criteriaBuilder.like(lowFieldName, lowSearchWord);

        Expression<String> loweredDescr = getLoweredField(from.get("description"), criteriaBuilder);
        Predicate predicateDescription = criteriaBuilder.like(loweredDescr, lowSearchWord);

        Predicate finalPredicate = criteriaBuilder.or(predicateName, predicateDescription);
        criteriaQuery.where(finalPredicate);


        return returnSearch(pageable, criteriaBuilder, criteriaQuery, from, select, finalPredicate);
    }
}
