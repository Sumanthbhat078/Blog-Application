package com.blogApp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogApp.entities.Category;
import com.blogApp.entities.Comment;
import com.blogApp.entities.Post;
import com.blogApp.entities.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payloads.CommentDto;
import com.blogApp.payloads.PostDto;
import com.blogApp.payloads.PostResponse;
import com.blogApp.repositories.CategoryRepo;
import com.blogApp.repositories.CommentRepo;
import com.blogApp.repositories.PostRepository;
import com.blogApp.repositories.UserRepository;
import com.blogApp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Post post=modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		Category category=catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		post.setUser(user);
		post.setCategory(category);
		postRepo.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
        Category category = this.catRepo.findById(postDto.getCategory().getCategoryId()).get();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
	}

	
	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		postRepo.delete(post);		
	}

	
	@Override
	public PostDto getPostById(int postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		return modelMapper.map(post,PostDto.class);
	}

	
	@Override
	public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy) {
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Page<Post> posts=postRepo.findAll(p);
		List<Post> allPosts=posts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse response=setPostResponse(postDtos,posts);
		return response;
	}
	
	
	@Override
	public PostResponse getPostsByCategory(Integer catId,int pageNumber,int pageSize,String sortBy) {
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Category cat=catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","id",catId));
		Page<Post> posts= postRepo.findByCategory(cat,p);
		List<Post> allPosts=posts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse response=setPostResponse(postDtos,posts);
		return response;
	}
	
	
	    @Override
	    public PostResponse getPostsByUser(Integer userId,int pageNumber,int pageSize,String sortBy) {
	    	Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
	        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
	        Page<Post> posts = this.postRepo.findByUser(user,p);
	        List<Post> allPosts=posts.getContent();
	        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());       
	        PostResponse response=setPostResponse(postDtos,posts);
			return response;
	    }

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=postRepo.searchByTitle(keyword);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());       
		return postDtos;
	}
	
	
	@Override
	public List<CommentDto> getAllComments(int postId) {
		List<Comment> comments=commentRepo.findByPost_PostId(postId);
		List<CommentDto> commentDtos = comments.stream().map((comment) -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());       
		return commentDtos;
	}
	
	public PostResponse setPostResponse(List<PostDto> postDtos,Page<Post> posts)
	{
		PostResponse response=new PostResponse();
		response.setContent(postDtos);
		response.setPageNumber(posts.getNumber());
		response.setPageSize(posts.getSize());
		response.setTotalElements(posts.getTotalElements());
		response.setTotalPages(posts.getTotalPages());
		response.setLastPage(posts.isLast());
		return response;
	}

	
	
}
