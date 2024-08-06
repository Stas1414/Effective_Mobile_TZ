package com.example.Task.Management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskDto {

    private Long id;

    private String heading;

    private String description;

    private String status;

    private String priority;

    private UserDto creator;

    private UserDto executor;

    private List<CommentDto> comments;
}
