package AmazonLike;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import AmazonLike.model.User;
import AmazonLike.repository.ProductRepository;
import AmazonLike.model.Category;
import AmazonLike.model.Product;
import AmazonLike.model.Role;
import AmazonLike.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class AmazonLike implements CommandLineRunner {

  final UserService userService;
  final ProductRepository productRepository;

  public static void main(String[] args) {
    SpringApplication.run(AmazonLike.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setFirstName("admin");
    admin.setLastName("admin");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

    userService.signup(admin);

    User client = new User();
    client.setUsername("client");
    client.setPassword("client");
    client.setLastName("client");
    client.setFirstName("client");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);

    Product iphone = new Product();
    iphone.setName("Iphone 14");
    iphone.setCategories(new ArrayList<Category>(Arrays.asList(Category.TECHNOLOGY)));
    iphone.setDescription("Un Iphone 14 avec des options mais on ne sait pas vraiment quoi mais il vaut le coup");
    iphone.setPrice(1800);
    iphone.setShortDescription("Iphone 14 UltraMaxMegaPlus");

    productRepository.save(iphone); 

  }

}
