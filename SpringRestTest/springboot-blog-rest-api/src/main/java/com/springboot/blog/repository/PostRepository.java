package com.springboot.blog.repository;


import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}