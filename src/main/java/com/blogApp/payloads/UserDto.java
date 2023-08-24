package com.blogApp.payloads;

import java.util.ArrayList;
import java.util.List;

import com.blogApp.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class UserDto {
	
   private int id;
   @NotEmpty
   @Size(min=4,message="name must have atleast 4 characters")
   private String name;
   @Email
   private String email;
   @NotEmpty
   @Size(min=3,max=10,message="password should be 3-10 chars")
   private String password;
   @NotNull
   private String about;
   private List<RoleDto> roles=new ArrayList<>();
   public List<RoleDto> getRoles() {
	return roles;
}
public void setRoles(List<RoleDto> roles) {
	this.roles = roles;
}
public UserDto() {
	super();
	// TODO Auto-generated constructor stub
}

public UserDto(int id, @NotEmpty @Size(min = 4, message = "name must have atleast 4 characters") String name,
		@Email String email,
		@NotEmpty @Size(min = 3, max = 10, message = "password should be 3-10 chars") String password,
		@NotNull String about, List<RoleDto> roles) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.about = about;
	this.roles = roles;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAbout() {
	return about;
}
public void setAbout(String about) {
	this.about = about;
}
}
