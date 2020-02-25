package com.stepovoy.domain.service;

import com.stepovoy.domain.model.user.Role;
import com.stepovoy.domain.model.user.RoleName;
import com.stepovoy.domain.model.user.User;
import com.stepovoy.domain.repository.UserRepository;
import com.stepovoy.exception.CommonAppException;
import com.stepovoy.exception.DuplicateEmailException;
import com.stepovoy.exception.DuplicateUserNameException;
import com.stepovoy.payload.user.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<User> findByUsernameOrEmail(String userNameOrEmail) {
        return userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public ResponseEntity<User> registerUser(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();
        if (existsByUsername(username)) {
            throw new DuplicateUserNameException("Username " + username + " is already taken!");
        }

        if (existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicateEmailException("Email " + email + " is already in use!");
        }


        User user = new User(signUpRequest.getName(), username,
                email, signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new CommonAppException("User Role not set"));

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
