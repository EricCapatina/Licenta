package com.licenta.demo.converter;

import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.UserDTO;

public class UserEntityToUserDTO {

    public UserDTO userModelToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        return userDto;
    }
}
