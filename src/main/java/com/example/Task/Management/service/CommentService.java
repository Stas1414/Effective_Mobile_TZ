package com.example.Task.Management.service;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.model.Comment;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CommentService {
    Comment createComment(Long taskId, String content);

    List<CommentDto> getAllCommentsFromTask(Long taskId);

    void deleteComment(Long commentId) throws AccessDeniedException;

    void updateComment(Long commentId, String content) throws AccessDeniedException;
}
