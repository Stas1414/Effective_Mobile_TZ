package com.example.Task.Management.controller;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "UserController", description = "It is intended to obtain information for the user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "Getting tasks for implementation")
    @GetMapping("/implementation")
    public ResponseEntity<List<TaskDto>> getAllTasksForImplementation() {
        return ResponseEntity.ok(userService.getAllTasksForImplementation());
    }

    @Operation(summary = "Getting created tasks")
    @GetMapping("/created")
    public ResponseEntity<List<TaskDto>> getAllCreatedTasks() {
        return ResponseEntity.ok(userService.getAllCreatedTasks());
    }

    @Operation(summary = "Getting tasks for a specific user to complete")
    @GetMapping("/{userId}/implementation")
    public ResponseEntity<List<TaskDto>> getTasksForImplementationById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getTasksForImplementationById(userId));
    }

    @Operation(summary = "Getting created tasks for a specific user")
    @GetMapping("/{userId}/created")
    public ResponseEntity<List<TaskDto>> getCreatedTaskById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getCreatedTaskById(userId));
    }

    @Operation(summary = "Allows you to change a status in the task")
    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> changeStatus(@PathVariable("taskId") Long taskId, @RequestBody String status) {
        try {
            userService.changeStatus(taskId, status);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

}
