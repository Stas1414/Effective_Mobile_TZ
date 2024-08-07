package com.example.Task.Management.service;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.model.Comment;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CommentService {
    Comment createComment(Long task_id, String content);

    List<CommentDto> getAllCommentsFromTask(Long task_id);

    void deleteComment(Long comment_id) throws AccessDeniedException;

    void updateComment(Long comment_id);
}
