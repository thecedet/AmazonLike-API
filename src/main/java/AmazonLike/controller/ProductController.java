package AmazonLike.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AmazonLike.model.Product;
import AmazonLike.payload.ProductsResponse;
import AmazonLike.repository.ProductRepository;
import AmazonLike.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository; 
    private final ProductService productService;

    @GetMapping("")
    public List<ProductsResponse> getAll() {
        return productRepository.findAll()
        .stream().map(product -> 
            modelMapper.map(product, ProductsResponse.class))
            .collect(Collectors.toList()
        );
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return productRepository.findById(id).get();
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product postMethodName(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

}
