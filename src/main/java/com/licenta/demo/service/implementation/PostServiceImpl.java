package com.licenta.demo.service.implementation;

import com.licenta.demo.converter.PostEntityToPostDTO;
import com.licenta.demo.dao.implementation.PostDAOImpl;
import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.dto.PostDTO;
import com.licenta.demo.database.entity.dto.PostDetailsDTO;
import com.licenta.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostDAOImpl postDAO;
    private final PostEntityToPostDTO converter;

    @Autowired
    public PostServiceImpl(PostDAOImpl postDAO, PostEntityToPostDTO converter) {
        this.postDAO = postDAO;
        this.converter = converter;
    }

    @Override
    public List<Post> getUserPosts(String userName) {
        return postDAO.getUserPosts(userName);
    }


    @Override
    public List<PostDTO> getAllPosts() {
        return converter.postModelToPostDto(postDAO.getAll());
    }

    @Override
    public List<PostDetailsDTO> getAllDetailedPosts() {
        return converter.postModelToPostDetailsDto(postDAO.getAll());
    }

    @Override
    public Post getPostById(Long id) {
        return postDAO.getPostById(id);
    }

    @Override
    public PostDTO getPostDetailById(Long id) {
        try {
            Post post = postDAO.getPostById(id);
            return converter.postModelToPostAuthorDto(post);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

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