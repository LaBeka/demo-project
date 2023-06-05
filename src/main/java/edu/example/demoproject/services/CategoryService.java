package edu.example.demoproject.services;

import edu.example.demoproject.entities.Category;
import edu.example.demoproject.repos.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> get() {
        Iterable<Category> all = categoryRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
    }

}