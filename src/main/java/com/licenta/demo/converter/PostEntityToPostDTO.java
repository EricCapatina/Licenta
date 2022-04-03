package com.licenta.demo.converter;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.dto.PostDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostEntityToPostDTO {

    public PostDTO postModelToPostDto(Post post) {
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setTimePlaced(post.getTimePlaced());
        postDto.setDisLikes(post.getDisLikes());
        postDto.setLikes(post.getLikes());
        return postDto;
    }

//    public PostDTO postModelToPostAuthorDto(Post post) {
//        PostDTO postDto = new PostDTO();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setTimePlaced(post.getTimePlaced());
//        postDto.setDisLikes(post.getDisLikes());
//        postDto.setLikes(post.getLikes());
//        return postDto;
//    }

    public List<PostDTO> postModelToPostDto(List<Post> posts) {
        return posts.stream().map(this::postModelToPostDto).collect(Collectors.toList());
    }
}
