package com.digitalremains.dataremoval.controller;

import com.digitalremains.dataremoval.dto.SupportedServiceDto;
import com.digitalremains.dataremoval.model.SupportedService;
import com.digitalremains.dataremoval.service.DataRemovalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/dataRemoval")
public class DataRemovalController {
    private final DataRemovalService dataRemovalService;

    @GetMapping("/services")
    public ResponseEntity<Collection<SupportedServiceDto>> getAvailableServices() {
        return ResponseEntity.ok().body(
            Arrays.stream(SupportedService.values())
                .map(SupportedServiceDto::convertToDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping
    public void removeDataFromServices(final SupportedService[] supportedServices) {
        dataRemovalService.removeAccounts(supportedServices);
    }
}
