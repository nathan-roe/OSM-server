package com.digitalremains.usermanagement.repository;

import com.digitalremains.usermanagement.model.ManagingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<ManagingUser, String> {
    Optional<ManagingUser> getManagingUserByEmailAddress(final String emailAddress);
}
