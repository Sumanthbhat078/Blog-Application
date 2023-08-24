package com.blogApp.services;

import com.blogApp.payloads.CommentDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto,int postId);
    void deleteComment(int commentId);
}
