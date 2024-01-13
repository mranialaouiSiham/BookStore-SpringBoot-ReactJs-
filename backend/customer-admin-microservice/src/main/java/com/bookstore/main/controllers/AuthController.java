package com.bookstore.main.controllers;


import com.bookstore.main.models.ERole;
import com.bookstore.main.models.Role;
import com.bookstore.main.models.User;
import com.bookstore.main.payload.request.LoginRequest;
import com.bookstore.main.payload.request.SignupRequest;
import com.bookstore.main.payload.response.JwtResponse;
import com.bookstore.main.payload.response.MessageResponse;
import com.bookstore.main.repository.RoleRepository;
import com.bookstore.main.repository.UserRepository;
import com.bookstore.main.security.jwt.JwtUtils;
import com.bookstore.main.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;


  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostConstruct
  public void initRoleAndUser(){
//    System.out.println("initRoleAndUser");
//    Role adminRole=new Role();
//    adminRole.setName(ERole.ROLE_ADMIN);
//    roleRepository.save(adminRole);
//
//    Role userRole=new Role();
//    userRole.setName(ERole.ROLE_USER);
//    roleRepository.save(userRole);
//
//    Set<Role> adminRoleSet=new HashSet<>();
//    adminRoleSet.add(adminRole);
//
//    Set<Role> userRoleSet=new HashSet<>();
//    userRoleSet.add(userRole);
//
//
//    User admin=new User();
//    admin.setEmail("admin@gmail.com");
//    admin.setPassword(encoder.encode("admin@gmail.com"));
//    admin.setRoles(adminRoleSet);
//    userRepository.save(admin);
//
//    User user=new User();
//    user.setEmail("user@gmail.com");
//    user.setPassword(encoder.encode("user@gmail.com"));
//    user.setRoles(userRoleSet);
//
//    userRepository.save(user);
//



  }
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    System.out.println(signUpRequest.getEmail());
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Role userRole=roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
            ()->new RuntimeException("Role not found")
    );
    Set<Role> roles = new HashSet<>();
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
