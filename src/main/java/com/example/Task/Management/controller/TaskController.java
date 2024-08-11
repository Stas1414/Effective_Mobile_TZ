package com.example.Task.Management.controller;

import com.example.Task.Management.model.Task;
import com.example.Task.Management.service.Impl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "TaskController", description = "Designed to work with tasks")
public class TaskController {

    private TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Creating a task")
    @PostMapping("/create")
    public ResponseEntity<Void> createTask(@RequestBody Task taskDetails) {
        taskService.createTask(taskDetails);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Allows you to update a task")
    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task taskDetails) {
        try {
            taskService.updateTask(taskId, taskDetails);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }


    @Operation(summary = "Allows you to delete a task")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.noContent().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
