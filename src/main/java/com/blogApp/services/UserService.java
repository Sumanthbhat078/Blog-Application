package com.blogApp.services;

import java.util.List;

import com.blogApp.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto UpdateUser(UserDto user,int userId);
    UserDto getUserById(int userId);
    List<UserDto> getAllUsers();
    void deleteUser(int userId);
}
