package com.digitalremains.deceasedmanagement.dto;

import com.digitalremains.common.dto.EntityDto;
import com.digitalremains.deceasedmanagement.model.DeceasedServiceOwner;
import com.digitalremains.deceasedmanagement.model.UserCreationProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeceasedServiceOwnerDto implements EntityDto<DeceasedServiceOwner> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String zipCode;
    private UserCreationProgress progress;

    @Override
    public DeceasedServiceOwner convertToEntity() {
        return DeceasedServiceOwner.builder()
            .firstName(firstName)
            .middleName(middleName)
            .lastName(lastName)
            .emailAddress(emailAddress)
            .phoneNumber(phoneNumber)
            .zipCode(zipCode)
            .progress(progress)
            .build();
    }
}
