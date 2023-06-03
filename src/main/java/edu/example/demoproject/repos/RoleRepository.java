package edu.example.demoproject.repos;

import edu.example.demoproject.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
