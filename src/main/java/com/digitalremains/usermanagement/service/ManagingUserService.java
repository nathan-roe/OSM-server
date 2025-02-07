package com.digitalremains.usermanagement.service;

import com.digitalremains.usermanagement.model.ManagingUser;
import com.digitalremains.usermanagement.repository.UserManagementRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagingUserService {
    private final UserManagementRepository userManagementRepository;

    public ManagingUser getAuthentication() {
        final Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return userManagementRepository.getManagingUserByEmailAddress(loggedInUser.getPrincipal().toString())
                .orElseThrow(() -> new BadCredentialsException("User must be signed in."));
    }
}
