package com.digitalremains.usermanagement.dto;

import com.digitalremains.common.dto.EntityDto;
import com.digitalremains.usermanagement.model.ManagingUser;
import lombok.Data;

@Data
public class ManagingUserDto implements EntityDto<ManagingUser> {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    public ManagingUser convertToEntity() {
        final ManagingUser managingUser = new ManagingUser();
        managingUser.setFirstName(firstName);
        managingUser.setLastName(lastName);
        managingUser.setPhoneNumber(phoneNumber);
        managingUser.setEmailAddress(emailAddress);
        managingUser.setPassword(password);
        managingUser.setIsVerified(Boolean.FALSE);
        return managingUser;
    }
}
