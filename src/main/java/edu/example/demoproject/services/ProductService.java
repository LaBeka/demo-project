package edu.example.demoproject.services;

import edu.example.demoproject.dtos.product.ProductSearchDto;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.entities.Product;
import edu.example.demoproject.repos.BrandRepository;
import edu.example.demoproject.repos.CategoryRepository;
import edu.example.demoproject.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
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

    private Long getTotalCount(CriteriaBuilder criteriaBuilder, List<Predicate> predicates){
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(criteriaBuilder.count(root));

        enManager.createQuery(criteriaQuery);
        Long total = 0L;
        for(int i = 0; i < predicates.size(); i++){
            criteriaQuery.where(predicates.get(i));
            TypedQuery<Long> query = enManager.createQuery(criteriaQuery);
            Long l = query.getSingleResult();
            total += l;
        }
        return total;
    }

    public PageImpl<Product> rawSearch(ProductSearchDto dto, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = enManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery =
                criteriaBuilder.createQuery(Product.class);

        Root<Product> from = criteriaQuery.from(Product.class);
        CriteriaQuery<Product> select = criteriaQuery.select(from);
        List<Predicate> predicates = new ArrayList<>();

        if (dto.getBrand() != null) {
            Predicate predicateBrand = criteriaBuilder.equal(from.get("brand"), dto.getBrand());
            criteriaQuery.where(predicateBrand);
            predicates.add(predicateBrand);
        }
        if (dto.getName() != null) {
            Predicate predicateName = criteriaBuilder.equal(from.get("name"), dto.getName());
            criteriaQuery.where(predicateName);
            predicates.add(predicateName);
        }
        if (dto.getDescription() != null) {
            Predicate predicateDescription = criteriaBuilder.like(from.get("description"), "%" + dto.getDescription() + "%");
            criteriaQuery.where(predicateDescription);
            predicates.add(predicateDescription);
        }
        if (dto.isNewProduct()) {
            Predicate predicateNewProduct = criteriaBuilder.equal(from.get("newProduct"), dto.isNewProduct());
            criteriaQuery.where(predicateNewProduct);
            predicates.add(predicateNewProduct);
        }
        if (dto.getCategory() != null) {
            Predicate predicateCategory = criteriaBuilder.equal(from.get("category"), dto.getCategory());
            criteriaQuery.where(predicateCategory);
            predicates.add(predicateCategory);
        }

        if (dto.isLoweredPrice()) {
            Predicate predicateLowered = criteriaBuilder.between(from.get("discount"), 1, 99);
            criteriaQuery.where(predicateLowered);
            predicates.add(predicateLowered);
        }

        return returnSearch(pageable, criteriaBuilder, criteriaQuery, from, select, predicates);
    }

    private PageImpl<Product> returnSearch(
            Pageable pageable,
            CriteriaBuilder criteriaBuilder,
            CriteriaQuery<Product> criteriaQuery,
            Root<Product> from,
            CriteriaQuery<Product> select,
            List<Predicate> predicates) {
        criteriaQuery.orderBy(criteriaBuilder.desc(from.get("currentPrice")));

        TypedQuery<Product> typedQuery = enManager.createQuery(select);

        typedQuery.setFirstResult(Math.toIntExact(pageable.getOffset()));
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Product> resultList = typedQuery.getResultList();
        Pageable pgbl = pageable;
        Long totalCount = getTotalCount(criteriaBuilder, predicates);
        PageImpl<Product> pagedProducts = new PageImpl<Product>(resultList, pgbl, totalCount);
        return pagedProducts;
    }

    public PageImpl<Product> searchInWord(String word, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = enManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery =
                criteriaBuilder.createQuery(Product.class);

        Root<Product> from = criteriaQuery.from(Product.class);
        CriteriaQuery<Product> select = criteriaQuery.select(from);
        List<Predicate> predicates = new ArrayList<>();


        predicates.add(criteriaBuilder.like(from.get("name"), "%" + word + "%"));
        predicates.add(criteriaBuilder.like(from.get("description"), "%" + word + "%"));

        return returnSearch(pageable, criteriaBuilder, criteriaQuery, from, select, predicates);
    }

}
