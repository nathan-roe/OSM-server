package com.digitalremains.deceasedmanagement.repository;

import com.digitalremains.deceasedmanagement.model.DeceasedServiceOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeceasedServiceOwnerRepository extends JpaRepository<DeceasedServiceOwner, String> {
}

