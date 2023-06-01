package edu.example.demoproject.services;

import edu.example.demoproject.dtos.product.ProductSearchDto;
import edu.example.demoproject.dtos.product.ProductCreateDto;
import edu.example.demoproject.entities.Product;
import edu.example.demoproject.repos.BrandRepository;
import edu.example.demoproject.repos.CategoryRepository;
import edu.example.demoproject.repos.ProductRepository;
import lombok.AllArgsConstructor;
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
    private final EntityManager entityManager;

    public boolean isAllowedAddNewProd(ProductCreateDto dto){
        Optional<Product> findIdenticalProduct = productRepository.findByNameAndBrand(dto.getName(), dto.getBrand());
        if (findIdenticalProduct.isPresent()){
            return true;
        }
        return false;
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

    public PageImpl<Product> rawSearch(ProductSearchDto dto, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
        if (dto.getLoweredPrice() != null) {
            Predicate predicateLowered = criteriaBuilder.equal(from.get("category"), dto.getCategory());
            criteriaQuery.where(predicateLowered);
            predicates.add(predicateLowered);
        }
        TypedQuery<Product> typedQuery = entityManager.createQuery(select);

        TypedQuery<Product> productTypedQuery = typedQuery.setFirstResult(Math.toIntExact(pageable.getOffset()));
        TypedQuery<Product> productTypedQuery1 = typedQuery.setMaxResults(pageable.getPageSize());

        PageImpl<Product> pagedProducts = new PageImpl<Product>(typedQuery.getResultList(), pageable, getTotalCount(criteriaBuilder, predicates));
        return pagedProducts;
    }
    private Long getTotalCount(CriteriaBuilder criteriaBuilder, List<Predicate> predicates){
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(predicateArray);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }


//    public List<Product> search(ProductSearchDto dto) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Product> criteriaQuery =
//                criteriaBuilder.createQuery(Product.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        Root<Product> from = criteriaQuery.from(Product.class);
//        CriteriaQuery<Product> select = criteriaQuery.select(from);
//        if (dto.getName() != null) {
//            Predicate namePredicate =
//                criteriaBuilder.like(from.get("name"), "%" +dto.getName() + "%");
//        }
//        if (dto.getBrand() != null) {
//            Predicate namePredicate =
//                    criteriaBuilder.like(from.get("brand"), "%" +dto.getName() + "%");
//            predicates.add(namePredicate);
//        }
//        if (dto.getCategory() != null) {
//            Predicate categoryPredicate =
//                    criteriaBuilder.like(from.get("category"), "%" +dto.getName() + "%");
//            predicates.add(categoryPredicate);
//        }
//        if (dto.getDescription() != null) {
//            Predicate descriptionPredicate =
//                    criteriaBuilder.like(from.get("description"), "%" +dto.getName() + "%");
//            predicates.add(descriptionPredicate);
//        }
//
//        criteriaQuery.where(
//                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
//        );
//
//        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
//
//        return typedQuery.getResultList();
//    }
}
