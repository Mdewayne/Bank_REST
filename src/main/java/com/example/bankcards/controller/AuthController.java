package com.example.bankcards.controller;

import com.example.bankcards.dto.UserRegistrationRequest;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.RoleService;
import com.example.bankcards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/register")

    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        Role role = roleService.getRoleByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(role);

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
