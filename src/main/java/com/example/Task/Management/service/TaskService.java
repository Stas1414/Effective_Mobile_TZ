package com.example.Task.Management.service;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    void updateTask(Long task_id, Long executor_id, Task task) throws AccessDeniedException;
    void deleteTask(Long task_id) throws AccessDeniedException;
}
