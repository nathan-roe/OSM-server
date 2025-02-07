package com.digitalremains.dataremoval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Getter
@AllArgsConstructor
public enum SupportedService {
    FACEBOOK("Facebook", "/static/facebook.png"),
    TWITTER("Twitter", "/static/facebook.png"),
    INSTAGRAM("Instagram", "/static/facebook.png"),
    GOOGLE("Google", "/static/google.png");

    final private String service;
    final private String resourcePath;

    public static SupportedService of(final String service) {
        return Arrays.stream(SupportedService.values())
                .filter(s -> s.getService().equals(service))
                .findFirst()
                .orElse(null);
    }

    public String getResource() {
        try (final InputStream inputStream = new ClassPathResource(this.resourcePath).getInputStream()) {
            return Base64.getEncoder().encodeToString(inputStream.readAllBytes());
        } catch(final IOException e) {
            log.error("Error retrieving service resource {}", e.getMessage());
            return null;
        }
    }

}
