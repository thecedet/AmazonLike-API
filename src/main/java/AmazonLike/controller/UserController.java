package AmazonLike.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AmazonLike.model.User;
import AmazonLike.payload.UserLoginRequest;
import AmazonLike.payload.UserSignup;
import AmazonLike.payload.UserResponse;
import AmazonLike.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  @PostMapping("/signin")
  public String login(@RequestBody UserLoginRequest data) {
    return userService.signin(data.getUsername(), data.getPassword());
  }

  @PostMapping("/signup")
  public String signup(@RequestBody UserSignup user) {
    return userService.signup(modelMapper.map(user, User.class));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String delete(@PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponse search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponse.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public UserResponse whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponse.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
