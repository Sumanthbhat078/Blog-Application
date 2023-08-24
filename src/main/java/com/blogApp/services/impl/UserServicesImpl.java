package com.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.entities.Role;
import com.blogApp.entities.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payloads.UserDto;
import com.blogApp.repositories.RoleRepo;
import com.blogApp.repositories.UserRepository;
import com.blogApp.services.UserService;
@Service
public class UserServicesImpl implements UserService{
	@Autowired
    private UserRepository userRepo;
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=convertToUser(userDto);
		return convertToUserDto(userRepo.save(user));
	}

	@Override
	public UserDto UpdateUser(UserDto userDto, int userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		userRepo.save(user);
		return convertToUserDto(user);
	}

	@Override
	public UserDto getUserById(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return convertToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.convertToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);
		
	}
    public User convertToUser(UserDto userDto)
    {
    	User user=modelMapper.map(userDto,User.class);
    	return user;
    }
    public UserDto convertToUserDto(User user)
    {
    	UserDto userDto=modelMapper.map(user,UserDto.class);
    	return userDto;
    }

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=modelMapper.map(userDto,User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role role=roleRepo.findById(2).orElseThrow(()->new ResourceNotFoundException("Role","id",userDto.getId()));;
		user.getRoles().add(role);
		User registeredUser=userRepo.save(user);
		return modelMapper.map(registeredUser, UserDto.class);
	}
    
}
