package com.example.Task.Management.controller;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.service.Impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "CommentController", description = "Designed to work with comments")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Creating comment")
    @PostMapping("/{taskId}")
    public ResponseEntity<Void> creatingComment(@PathVariable("taskId") Long taskId, @RequestBody String content) {
        commentService.createComment(taskId, content);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Getting comments from a specific task")
    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentsFromTask(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(commentService.getAllCommentsFromTask(taskId));
    }

    @Operation(summary = "Allows you to update the comment")
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Long commentId, @RequestBody String content) {
        try {
            commentService.updateComment(commentId, content);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @Operation(summary = "Allows you to delete a comment")
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
