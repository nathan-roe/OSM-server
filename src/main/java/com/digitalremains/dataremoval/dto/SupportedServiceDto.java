package com.digitalremains.dataremoval.dto;

import com.digitalremains.dataremoval.model.SupportedService;
import com.digitalremains.common.dto.EntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportedServiceDto implements EntityDto<SupportedService> {
    private String service;
    private String resource;

    public static SupportedServiceDto convertToDto(final SupportedService entity) {
        final SupportedServiceDto dto = new SupportedServiceDto();
        dto.setService(entity.getService());
        dto.setResource(entity.getResource());
        return dto;
    }
}
