package com.digitalremains.dataremoval.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportedServiceRequestDto {
    private String[] services;
}
