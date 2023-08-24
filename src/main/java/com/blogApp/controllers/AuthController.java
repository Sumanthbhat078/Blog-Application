package com.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.exceptions.ApiException;
import com.blogApp.payloads.JwtAuthRequest;
import com.blogApp.payloads.JwtAuthResponse;
import com.blogApp.payloads.UserDto;
import com.blogApp.repositories.UserRepository;
import com.blogApp.security.JwtTokenHelper;
import com.blogApp.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	   @Autowired
	   private JwtTokenHelper jwtTokenHelper;
	   
	   @Autowired
	   private AuthenticationManager authenticationManager;
	
	   @Autowired
	   private UserDetailsService userDetailsService;
	   
	   @Autowired
	   private UserService userService;
	   
	   @Autowired
	   private UserRepository userRepo;
	   
	   @PostMapping("/login")
	   public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	   {
		   UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
		   this.authenticate(request.getUserName(),request.getPassword());
		   String token=jwtTokenHelper.generateToken(userDetails);
		   JwtAuthResponse response=new JwtAuthResponse();
		   response.setToken(token);
		   return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	   }
	   
	   private void authenticate(String userName,String password) throws Exception
	   {
		   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
		   try {
		   this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		   }
		   catch(BadCredentialsException e)
		   {
			   System.out.println("invalid details");
			   throw new ApiException("invalid credentials");
		   }
	   }
	   
	   @PostMapping("/register")
	   public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
	   {
		   UserDto registeredUser=userService.registerNewUser(userDto);
		   return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	   }
}
