package com.digitalremains.usermanagement.service.authentication;

import com.digitalremains.usermanagement.model.ManagingUser;
import com.digitalremains.usermanagement.repository.UserManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserManagementRepository userManagementRepository;

    @Override
    public UserDetails loadUserByUsername(final String emailAddress) throws UsernameNotFoundException {
        final ManagingUser managingUser = userManagementRepository.getManagingUserByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Email address not found: %s", emailAddress)
                ));
        return new User(
                managingUser.getEmailAddress(),
                managingUser.getPassword(),
                Collections.emptyList()
        );
    }
}
