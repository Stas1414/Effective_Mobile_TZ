package com.example.Task.Management.service;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface TaskService {
    void createTask(Task task);

    void updateTask(Long taskId, Task task) throws AccessDeniedException;

    void deleteTask(Long taskId) throws AccessDeniedException;
}
