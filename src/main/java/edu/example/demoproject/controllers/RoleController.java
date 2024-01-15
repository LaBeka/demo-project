package edu.example.demoproject.controllers;

import edu.example.demoproject.api.RoleApi;
import edu.example.demoproject.repos.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoleController implements RoleApi {
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> getListRoles() {
        return ResponseEntity.ok().body(roleRepository.getRoles());
    }
}
