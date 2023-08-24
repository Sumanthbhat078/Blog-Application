package com.blogApp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogApp.entities.Role;
import com.blogApp.repositories.RoleRepo;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Bean
	public ModelMapper  modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("password: "+passwordEncoder.encode("sum#123"));
		
		Role role1=new Role();
		role1.setId(1);
		role1.setName("ADMIN_USER");
		Role role2=new Role();
		role2.setId(2);
		role2.setName("NORMAL_USER");
		List<Role> roles=List.of(role1,role2);
		roleRepo.saveAll(roles);
	}
}
