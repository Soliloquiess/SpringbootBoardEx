package com.springboot.blog.service;


import com.springboot.blog.payload.PostDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

}

