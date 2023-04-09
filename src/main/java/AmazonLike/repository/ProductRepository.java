package AmazonLike.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import AmazonLike.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  boolean existsByName(String name);

  Product findByName(String name);

  @Transactional
  void deleteByName(String name);

}