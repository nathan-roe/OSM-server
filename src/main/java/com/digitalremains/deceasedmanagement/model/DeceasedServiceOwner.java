package com.digitalremains.deceasedmanagement.model;

import com.digitalremains.usermanagement.model.ManagingUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeceasedServiceOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String zipCode;
    @JsonIgnore
    @OneToOne
    private FileUpload deathCertificate;
    @JsonIgnore
    @OneToOne
    private FileUpload governmentId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    private ManagingUser managingUser;
    private String servicesForRemoval;
    private UserCreationProgress progress;
}