package com.digitalremains.deceasedmanagement.controller;

import com.digitalremains.dataremoval.dto.SupportedServiceRequestDto;
import com.digitalremains.dataremoval.model.SupportedService;
import com.digitalremains.deceasedmanagement.dto.DeceasedServiceOwnerDto;
import com.digitalremains.deceasedmanagement.model.DeceasedServiceOwner;
import com.digitalremains.deceasedmanagement.model.FileUpload;
import com.digitalremains.deceasedmanagement.model.UserCreationProgress;
import com.digitalremains.deceasedmanagement.repository.DeceasedServiceOwnerRepository;
import com.digitalremains.deceasedmanagement.repository.FileUploadRepository;
import com.digitalremains.usermanagement.model.ManagingUser;
import com.digitalremains.usermanagement.service.ManagingUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "deceasedUserManagement")
public class DeceasedServiceOwnerController {
    private final DeceasedServiceOwnerRepository deceasedServiceOwnerRepository;
    private final FileUploadRepository fileUploadRepository;
    private final ManagingUserService managingUserService;

    @PostMapping
    public ResponseEntity<DeceasedServiceOwner> createDeceasedUser(@RequestBody final DeceasedServiceOwnerDto deceasedServiceOwnerDto) {
        final ManagingUser managingUser = managingUserService.getAuthentication();
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerDto.convertToEntity();
        serviceOwner.setProgress(UserCreationProgress.USER_INFO);
        serviceOwner.setManagingUser(managingUser);
        deceasedServiceOwnerRepository.save(serviceOwner);
        return ResponseEntity.ok().body(serviceOwner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeceasedServiceOwner> updateDeceasedUser(@PathVariable final Long id, @RequestBody final DeceasedServiceOwnerDto deceasedServiceOwnerDto) {
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerRepository.getReferenceById(String.valueOf(id));
        final DeceasedServiceOwner updatedOwner = deceasedServiceOwnerDto.convertToEntity();
        serviceOwner.setFirstName(updatedOwner.getFirstName());
        serviceOwner.setMiddleName(updatedOwner.getMiddleName());
        serviceOwner.setLastName(updatedOwner.getLastName());
        serviceOwner.setEmailAddress(updatedOwner.getEmailAddress());
        serviceOwner.setPhoneNumber(updatedOwner.getPhoneNumber());
        serviceOwner.setZipCode(updatedOwner.getZipCode());
        serviceOwner.setProgress(updatedOwner.getProgress());
        deceasedServiceOwnerRepository.save(serviceOwner);
        return ResponseEntity.ok().body(serviceOwner);
    }

    @GetMapping("/{id}/progress")
    public ResponseEntity<UserCreationProgress> getDeceasedUserDataProgress(@PathVariable final Long id) {
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerRepository.getReferenceById(String.valueOf(id));
        return ResponseEntity.ok().body(serviceOwner.getProgress());
    }

    @PostMapping("/{id}/certificate")
    public ResponseEntity<Void> addCertificateToDeceasedUser(@PathVariable final Long id, @RequestBody final FileUpload fileUpload) {
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerRepository.getReferenceById(String.valueOf(id));
        final FileUpload certificate = fileUploadRepository.save(fileUpload);
        serviceOwner.setDeathCertificate(certificate);
        serviceOwner.setProgress(UserCreationProgress.CERTIFICATE);
        deceasedServiceOwnerRepository.save(serviceOwner);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/identification")
    public ResponseEntity<Void> addIdentificationToDeceasedUser(@PathVariable final Long id, @RequestBody final FileUpload fileUpload) {
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerRepository.getReferenceById(String.valueOf(id));
        final FileUpload identification = fileUploadRepository.save(fileUpload);
        serviceOwner.setGovernmentId(identification);
        serviceOwner.setProgress(UserCreationProgress.IDENTIFICATION);
        deceasedServiceOwnerRepository.save(serviceOwner);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/services")
    public ResponseEntity<Void> addServicesToDeceasedUser(@PathVariable final Long id, @RequestBody final SupportedServiceRequestDto services) {
        final DeceasedServiceOwner serviceOwner = deceasedServiceOwnerRepository.getReferenceById(String.valueOf(id));
        serviceOwner.setServicesForRemoval(Arrays.stream(services.getServices())
            .map(s -> SupportedService.of(s).getService())
            .collect(Collectors.joining(","))
        );
        serviceOwner.setProgress(UserCreationProgress.SERVICE_SELECTION);
        deceasedServiceOwnerRepository.save(serviceOwner);
        return ResponseEntity.ok().build();
    }
}
