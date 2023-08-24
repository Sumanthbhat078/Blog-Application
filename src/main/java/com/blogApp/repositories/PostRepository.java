package com.blogApp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogApp.entities.Category;
import com.blogApp.entities.Post;
import com.blogApp.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{

	Page<Post> findByCategory(Category cat,Pageable p);

	Page<Post> findByUser(User user,Pageable p);
	
	
	@Query("select p from Post p where p.title like %:title%")
	List<Post> searchByTitle(@Param("title") String title);
	
	
	//List<Post> findByTitleContaining(String title);

}
