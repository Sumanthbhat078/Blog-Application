package com.blogApp.services;

import java.util.List;

import com.blogApp.payloads.CommentDto;
import com.blogApp.payloads.PostDto;
import com.blogApp.payloads.PostResponse;

public interface PostService {
  PostDto createPost(PostDto post,int userId,int categoryId);
  PostDto updatePost(PostDto post,int postId);
  void deletePost(Integer postId);
  PostDto getPostById(int postId);
  PostResponse  getAllPosts(int pageSize,int pageNumber,String sortBy);
  PostResponse getPostsByCategory(Integer catId,int pageSize,int pageNumber,String sortBy);
  PostResponse getPostsByUser(Integer userId,int pageSize,int pageNumber,String sortBy);
  List<CommentDto> getAllComments(int postId);
  List<PostDto> searchPosts(String keyword);
  
}
