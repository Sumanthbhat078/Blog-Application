package com.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{
   
}
