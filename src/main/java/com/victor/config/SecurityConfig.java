package com.victor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	
//    @Autowired
//    JwtFilter jwtFilter; // Inyecta el filtro personalizado JwtFilter

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.out.println("Entro por aquí");
		http.csrf( csrf -> csrf.disable()).authorizeHttpRequests(
				authz -> { authz.antMatchers("/gestionHello/helloWorld").permitAll()
				//.antMatchers("/gestionHello/helloWorld2").permitAll()
				.anyRequest().authenticated();
				});
				//.addFilterBefore(jwtFilter, OncePerRequestFilter.class);
		return	http.build();		
	}

	
}
