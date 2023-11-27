package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "select p from ProductEntity p where p.name = :name and p.brand = :brand")
    Optional<ProductEntity> findByNameAndBrand(String name, Brand brand);

}
