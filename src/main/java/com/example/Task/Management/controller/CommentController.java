package com.example.Task.Management.controller;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentsFromTask(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(commentService.getAllCommentsFromTask(taskId));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Long commentId, @RequestBody String content) {
        try {
            commentService.updateComment(commentId, content);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
