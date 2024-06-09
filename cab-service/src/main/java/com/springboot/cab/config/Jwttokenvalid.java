package com.springboot.cab.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Jwttokenvalid  extends OncePerRequestFilter{

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt= request.getHeader(jwtcontant.jwtheader);
		
		if (jwt!=null) {
			jwt= jwt.substring(7);
			try {
				
				SecretKey key= Keys.hmacShaKeyFor(jwtcontant.key.getBytes());
				Claims claim= Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				String email= String.valueOf(claim.get("email"));
				String authorities= String.valueOf(claim.get("authorities"));
				List< GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication auth =new UsernamePasswordAuthenticationToken(email,null, authority);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}catch (Exception e) {
				throw new BadCredentialsException("invalid token ");
			}
			
		}
		filterChain.doFilter(request, response);
		
	}



}
