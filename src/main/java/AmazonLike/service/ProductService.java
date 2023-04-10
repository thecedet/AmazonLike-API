package AmazonLike.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import AmazonLike.exception.CustomException;
import AmazonLike.model.Product;
import AmazonLike.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    public Product update(Integer id, Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);
        if(oldProduct == null) {
            throw new CustomException("Produit introuvable", HttpStatus.NOT_FOUND);
        }
        productRepository.save(product);
        return product;
    }

}
