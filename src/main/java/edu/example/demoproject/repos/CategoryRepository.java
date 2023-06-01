package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
