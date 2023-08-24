package com.blogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.config.AppConstants;
import com.blogApp.payloads.ApiResponse;
import com.blogApp.payloads.CommentDto;
import com.blogApp.payloads.PostDto;
import com.blogApp.payloads.PostResponse;
import com.blogApp.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
    private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid 
			@RequestBody PostDto postDto, 
			@PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@PathVariable("categoryId") Integer catId,
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy)
	{
		PostResponse response=postService.getPostsByCategory(catId,pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(
			@PathVariable("userId") Integer userId,
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) int pageSize,
	        @RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy)
	{
		PostResponse response=postService.getPostsByUser(userId,pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy)
	{
		PostResponse response=postService.getAllPosts(pageNumber, pageSize,sortBy);
		return new ResponseEntity<PostResponse>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/post/{postId}")
	public PostDto getPostById(@PathVariable("postId") int postId)
	{
		PostDto postDto=postService.getPostById(postId);
		return postDto;
	}
	
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable("postId") int postId)
	{
		postService.deletePost(postId);
		return new ApiResponse("post deleted successfully",true);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostDto postDto,
			@PathVariable("postId") int postId)
	{
		PostDto updatedPost=postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword)
	{
		List<PostDto> postDtos=postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}/getComments")
	ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("postId") int postId)
	{
		List<CommentDto> comments=postService.getAllComments(postId);
		return new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK);
	}
	
	
	
}
