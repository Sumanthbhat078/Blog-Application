package com.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

}
