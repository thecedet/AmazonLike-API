package AmazonLike.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import AmazonLike.exception.CustomException;
import AmazonLike.model.Role;
import AmazonLike.model.User;
import AmazonLike.payload.UserResponse;
import AmazonLike.payload.UserSignup;
import AmazonLike.repository.UserRepository;
import AmazonLike.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    if(username == null) throw new CustomException("Paramètre `username` manquant", HttpStatus.BAD_REQUEST);
    if(password == null) throw new CustomException("Paramètre `password` manquant", HttpStatus.BAD_REQUEST);

    try {
      this.search(username);
    }catch (CustomException e) {
      User user = userRepository.findByEmail(username);
      if(user == null) {
        throw new CustomException("Impossible de se connecter (mauvais login/password)", HttpStatus.UNAUTHORIZED);
      }else username = user.getUsername();
    }

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Impossible de se connecter (mauvais login/password)", HttpStatus.UNAUTHORIZED);
    }
  }

  public String signup(User user) {
    if(user.getRoles() == null) user.setRoles(Arrays.asList(Role.ROLE_CLIENT));
    if(userRepository.existsByUsername(user.getUsername())) throw new CustomException("L'utilisateur existe déjà", HttpStatus.CONFLICT);
    if(userRepository.existsByEmail(user.getEmail())) throw new CustomException("L'email existe déjà", HttpStatus.CONFLICT);
    
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if(user == null) {
      throw new CustomException("Utilisateur introuvable", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User update(String username, UserResponse data) {
    User user = this.search(username);
    user.setUsername(data.getUsername());
    user.setEmail(data.getEmail());
    user.setFirstName(data.getFirstName());
    user.setLastName(data.getLastName());
    user.setRoles(data.getRoles());
    
    userRepository.save(user);

    return user;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public User updateMe(HttpServletRequest req, UserSignup data) {
    User user = this.whoami(req);
    user.setUsername(data.getUsername());
    user.setEmail(data.getEmail());
    user.setFirstName(data.getFirstName());
    user.setLastName(data.getLastName());

    userRepository.save(user);

    return user;

  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

}
