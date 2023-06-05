package edu.example.demoproject.services;

import edu.example.demoproject.entities.Brand;
import edu.example.demoproject.entities.Product;
import edu.example.demoproject.repos.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    public List<Brand> get() {
        Iterable<Brand> all = brandRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                        .collect(Collectors.toList());
    }

}
