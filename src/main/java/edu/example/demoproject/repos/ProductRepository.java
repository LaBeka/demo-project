package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "select p from Product  as p where p.name = :name and p.brand = :brand")
    Optional<Product> findByNameAndBrand(String name, Brand brand);
}
