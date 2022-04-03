package com.licenta.demo.dao;

import com.licenta.demo.database.entity.Post;

import java.util.List;

public interface PostDAO {

    List<Post> getUserPosts(String userName);

    Post getPostById(Long id);

}