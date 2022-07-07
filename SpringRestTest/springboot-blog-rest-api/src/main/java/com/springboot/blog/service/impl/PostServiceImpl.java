package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

//    @Autowired //생성자가 하나라면 Autowired주석 생략 가능
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto){

       //convert DTO to entity
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);   //이 post객체를 세이브 메서드로 전달
        //DTO를 엔티티로 만들고 엔티티를 다시 DTO로 만든다.
        //convert entity to DTO
        PostDto postResponse= mapToDTO(newPost);
//        PostDto postResponse= new PostDto();
//        postResponse.setId(newPost.getId());
//        postResponse.setTitle(newPost.getTitle());
//        postResponse.setDescription(newPost.getDescription());
//        postResponse.setContent(newPost.getContent());
        return postResponse;

    }

    @Override
    public List<PostDto> getAllPosts() {    //완료시 post클래스로 이동하게 됨.
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());
//        return null;
    }

    //로직을 다 여기로 옮김.
    //엔티티에서 DTO로
    private PostDto mapToDTO(Post post){
        PostDto postDto  = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //DTO에서엔티티로 변환
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
