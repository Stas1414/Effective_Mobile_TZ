package com.example.Task.Management.controller;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/implementation")
    public ResponseEntity<List<TaskDto>> getAllTasksForImplementation() {
        return ResponseEntity.ok(userService.getAllTasksForImplementation());
    }

    @GetMapping("/created")
    public ResponseEntity<List<TaskDto>> getAllCreatedTasks() {
        return ResponseEntity.ok(userService.getAllCreatedTasks());
    }

    @GetMapping("/{userId}/implementation")
    public ResponseEntity<List<TaskDto>> getTasksForImplementationById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getTasksForImplementationById(userId));
    }

    @GetMapping("/{userId}/created")
    public ResponseEntity<List<TaskDto>> getCreatedTaskById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getCreatedTaskById(userId));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> changeStatus(@PathVariable("taskId") Long taskId, @RequestBody String status) {
        userService.changeStatus(taskId, status);
        return ResponseEntity.noContent().build();
    }

}
