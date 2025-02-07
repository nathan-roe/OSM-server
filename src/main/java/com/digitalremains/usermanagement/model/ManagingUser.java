package com.digitalremains.usermanagement.model;

import com.digitalremains.deceasedmanagement.model.DeceasedServiceOwner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    @JsonIgnore
    private String password;
    private Boolean isVerified;
    @OneToMany(mappedBy = "managingUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DeceasedServiceOwner> deceasedServiceOwner;
}
