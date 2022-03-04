package com.licenta.demo.service.implementation;

import com.licenta.demo.dao.PostDAO;
import com.licenta.demo.dao.implementation.PostDAOImpl;
import com.licenta.demo.database.entity.Post;
import com.licenta.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostDAOImpl postDAO;

    @Autowired
    public PostServiceImpl(PostDAOImpl postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    public List<Post> getUserPosts(String userName) {
        return postDAO.getUserPosts(userName);
    }


    @Override
    public List<Post> getAllPosts() {
        return postDAO.getAll();
    }

    @Override
    public Post getPostById(long id) {
        return postDAO.getById(id);
    }

    @Override
    public Post createPost(Post task) {
        return postDAO.add(task);
    }

    @Override
    public void deletePostByID(Long id) {
        postDAO.removeById(id);
    }
}