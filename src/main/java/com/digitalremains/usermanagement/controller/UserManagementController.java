package com.digitalremains.usermanagement.controller;

import com.digitalremains.usermanagement.dto.LoginRequest;
import com.digitalremains.usermanagement.dto.ManagingUserDto;
import com.digitalremains.usermanagement.model.ManagingUser;
import com.digitalremains.usermanagement.repository.UserManagementRepository;
import com.digitalremains.usermanagement.service.ManagingUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/userManagement")
public class UserManagementController {

    private final AuthenticationManager authenticationManager;
    private final UserManagementRepository userManagementRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();
    private final PasswordEncoder passwordEncoder;
    private final ManagingUserService managingUserService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody final LoginRequest loginRequest,
                                      final HttpServletRequest request,
                                      final HttpServletResponse response) {
        final Authentication token = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        final Authentication authentication = authenticationManager.authenticate(token);
        final SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createManagingUser(@RequestBody final ManagingUserDto managingUserDto) {
        final ManagingUser managingUser = managingUserDto.convertToEntity();
        managingUser.setPassword(passwordEncoder.encode(managingUser.getPassword()));
        userManagementRepository.save(managingUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<ManagingUser> getCurrentManagingUser() {
        return ResponseEntity.ok().body(managingUserService.getAuthentication());
    }

}
