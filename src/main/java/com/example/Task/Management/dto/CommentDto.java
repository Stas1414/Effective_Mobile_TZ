package com.example.Task.Management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private String content;

    private UserDto author;

}
