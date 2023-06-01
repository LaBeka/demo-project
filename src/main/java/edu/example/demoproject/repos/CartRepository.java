package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
