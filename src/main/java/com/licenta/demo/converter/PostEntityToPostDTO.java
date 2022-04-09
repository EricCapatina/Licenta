package com.licenta.demo.converter;

import com.licenta.demo.database.entity.Comment;
import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.dto.PostDTO;
import com.licenta.demo.database.entity.dto.PostDetailsDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
        postDto.setAuthor(post.getAuthor());
        postDto.setFormattimePlaced(post.getTimePlaced());
        return postDto;
    }

    public PostDetailsDTO postModelToPostDetailsDto(Post post) {
        PostDetailsDTO postDto = new PostDetailsDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setTimePlaced(post.getTimePlaced());
        return postDto;
    }

    public PostDTO postModelToPostAuthorDto(Post post) {
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setTimePlaced(post.getTimePlaced());
        postDto.setDisLikes(post.getDisLikes());
        postDto.setLikes(post.getLikes());
        postDto.setAuthor(post.getAuthor());
        postDto.setTextData(post.getTextData());
//        postDto.setComments(
//                post.getComments()
//                        .stream()
//                        .map(Comment::toString)
//                        .collect(Collectors.toList()));
        postDto.setComments(new ArrayList<>(post.getComments()));
        return postDto;
    }

    public List<PostDTO> postModelToPostDto(List<Post> posts) {
        return posts.stream().map(this::postModelToPostDto).collect(Collectors.toList());
    }

    public List<PostDetailsDTO> postModelToPostDetailsDto(List<Post> posts) {
        return posts.stream().map(this::postModelToPostDetailsDto).collect(Collectors.toList());
    }
}
