package edu.example.demoproject.repos;

import edu.example.demoproject.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
