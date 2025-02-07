package com.digitalremains.usermanagement.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/userManagement/login").permitAll()
                        .requestMatchers("/userManagement/register").permitAll()
                        .requestMatchers("/**").fullyAuthenticated()
                        .anyRequest().authenticated()
                );
        http.securityContext(c -> c.securityContextRepository(securityContextRepository));
        http.sessionManagement(session -> {
            session.maximumSessions(1).maxSessionsPreventsLogin(true);
            session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession);
            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        });
        http.logout(logout -> {
            logout.logoutUrl("/userManagement/logout");
            logout.addLogoutHandler(
                    new HeaderWriterLogoutHandler(
                            new ClearSiteDataHeaderWriter(
                                    ClearSiteDataHeaderWriter.Directive.COOKIES
                            )
                    )
            );
            logout.deleteCookies("JSESSIONID");
        });
        http.authenticationProvider(authenticationProvider);
        return http.build();
    }
}