package com.example.Task.Management.mapping;

import com.example.Task.Management.dto.UserDto;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingUser {

    private UserRepository userRepository;

    @Autowired
    public MappingUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        return userRepository.findById(userDto.getId()).orElseThrow(() -> new NullPointerException("The entity was not founded"));
    }

}
