package com.blogApp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String requestToken=request.getHeader("Authorization");
		 System.out.println(requestToken);
		 String userName=null;
		 String token=null;
		 if(requestToken!=null && requestToken.startsWith("Bearer"))
		 {
			 token=requestToken.substring(7);
			 try {
				 userName=jwtTokenHelper.getUsernameFromToken(token);
				}
			 catch (IllegalArgumentException e) {
					System.out.println("Unable to get Jwt token");
				} 
			 catch (ExpiredJwtException e) {
					System.out.println("Jwt token has expired");
				} 
			 catch (MalformedJwtException e) {
					System.out.println("invalid jwt");
			    }
			 
		 }
		 else
		 {
			 System.out.println("jwt token doesnt begin with Bearer");
		 }
		 
		 if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		 {
			 UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			 if(jwtTokenHelper.validateToken(token, userDetails))
			 {
				 UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				 userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
			 }
			 else
			 {
				 System.out.println("invalid jwt token");
			 }
		 }
		 else
		 {
			 System.out.println("user name is null or context is not null");
		 }
		 filterChain.doFilter(request, response);
	}

}
