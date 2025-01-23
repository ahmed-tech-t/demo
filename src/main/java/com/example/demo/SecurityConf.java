package com.example.demo;

import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConf {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/employees")
                        .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name(), Role.EMPLOYEE.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/employees/**")
                        .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name(), Role.EMPLOYEE.name()))

                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/employees")
                        .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/employees/**")
                        .hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name()))

                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/employees")
                        .hasRole(Role.ADMIN.name()))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
