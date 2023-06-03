package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
