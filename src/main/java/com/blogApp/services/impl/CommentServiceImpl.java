package com.blogApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.entities.Comment;
import com.blogApp.entities.Post;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payloads.CommentDto;
import com.blogApp.repositories.CommentRepo;
import com.blogApp.repositories.PostRepository;
import com.blogApp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		Comment comment=modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment newComment=commentRepo.save(comment);
		return modelMapper.map(newComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment=commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
		commentRepo.delete(comment);
	}

}
