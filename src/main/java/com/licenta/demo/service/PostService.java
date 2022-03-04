package com.licenta.demo.service;

import com.licenta.demo.database.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getUserPosts(String userName);

    List<Post> getAllPosts();

    Post getPostById(long id);

    Post createPost(Post task) throws Exception;

    void deletePostByID(Long id);
}