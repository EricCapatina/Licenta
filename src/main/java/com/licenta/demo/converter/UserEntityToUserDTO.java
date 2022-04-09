package com.licenta.demo.converter;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.PostDetailsDTO;
import com.licenta.demo.database.entity.dto.UserDTO;
import com.licenta.demo.database.entity.dto.UserDetailsDTO;
import com.licenta.demo.database.entity.dto.UserPostDetailsDTO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityToUserDTO {

    private final static String DATEPATTERN = "yyyy-MM-dd";

    public UserDTO userModelToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        return userDto;
    }


    public UserDetailsDTO getUserMarkedPostsDTO(User user) {
        UserDetailsDTO userDto = new UserDetailsDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setMarkedTasks(user.getPosts()
                .stream()
                .map(t -> new UserPostDetailsDTO(t.getId(), t.getTitle(), t.getTimePlaced().format(DateTimeFormatter.ofPattern(DATEPATTERN))))
                .collect(Collectors.toList()));
        return userDto;
    }

    public UserDetailsDTO userModelToUserDetailsDto(User user) {
        UserDetailsDTO userDetailsDto = new UserDetailsDTO();
        userDetailsDto.setRole(user.getRole());
        userDetailsDto.setTasks(
                user.getPosts()
                        .stream()
                        .peek(t -> {
                            t.setAuthor("");
                            t.setComments(new ArrayList<>());
                        })
                        .collect(Collectors.toList()));
        userDetailsDto.setId(user.getId());
        userDetailsDto.setUserName(user.getUserName());
        userDetailsDto.setFirstName(user.getFirstName());
        userDetailsDto.setLastName(user.getLastName());
        return userDetailsDto;
    }

    public List<UserDTO> userModelToUserDto(List<User> users) {
        return users.stream().map(this::userModelToUserDto).collect(Collectors.toList());
    }
}
