package com.springboot.cab.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class appconfig {
	
	
@Bean
public SecurityFilterChain securityfilterchain(HttpSecurity http)throws Exception{
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests(Auth->Auth.requestMatchers("/auth/**").permitAll()
			.anyRequest().authenticated())
		.addFilterBefore(new Jwttokenvalid(),BasicAuthenticationFilter.class)
		.csrf().disable()
		.cors().configurationSource(corsConfigurationSource()).and()
		.httpBasic().and()
		.formLogin();
		
		
		
		return http.build();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		// TODO Auto-generated method stub
		return  new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg= new CorsConfiguration();
				ArrayList<String> a = new ArrayList<>();
				a.add("http://localhost:3000/");
				cfg.setAllowedOrigins(a);
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				ArrayList<String> b = new ArrayList<>();
				b.add("Authorization");
				cfg.setExposedHeaders(b);
				cfg.setMaxAge(36000L);
				return cfg;
				
			} 
		
		};
	}
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
