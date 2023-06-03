package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
}
