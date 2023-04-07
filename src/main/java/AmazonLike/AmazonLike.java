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
import AmazonLike.model.Role;
import AmazonLike.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class AmazonLike implements CommandLineRunner {

  final UserService userService;

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
    admin.setAppUserRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

    userService.signup(admin);

    User client = new User();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setAppUserRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);
  }

}