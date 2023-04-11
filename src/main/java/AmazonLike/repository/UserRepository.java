package AmazonLike.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import AmazonLike.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByEmail(String email);

  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

  boolean existsByEmail(String email);

}
