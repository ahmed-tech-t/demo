package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.service.MyDetailsService;
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
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConf {
    MyDetailsService myDetailsService;

    @Autowired
    SecurityConf(MyDetailsService service) {
        this.myDetailsService = service;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(Role.Admin.name());
        return http
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/employees")
                        .hasAnyRole(Role.Admin.name(), Role.Manager.name(), Role.Employee.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/employees/**")
                        .hasAnyRole(Role.Admin.name(), Role.Manager.name(), Role.Employee.name()))

                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/employees")
                        .hasAnyRole(Role.Admin.name(), Role.Manager.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/employees/**")
                        .hasAnyRole(Role.Admin.name(), Role.Manager.name()))

                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/employees")
                        .hasRole(Role.Admin.name()))
                .httpBasic(Customizer.withDefaults())
              //  .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(myDetailsService);
        return provider;
    }

}
