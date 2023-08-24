package com.blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer>{
     public List<Comment> findByPost_PostId(int postId);	
	
}
