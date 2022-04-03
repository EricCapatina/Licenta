package com.licenta.demo.service;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<Post> getUserPosts(String userName);

    List<PostDTO> getAllPosts();

    Post getPostById(Long id);

    Post createPost(Post task) throws Exception;

    void deletePostByID(Long id);
}