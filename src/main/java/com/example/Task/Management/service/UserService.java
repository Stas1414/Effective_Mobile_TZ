package com.example.Task.Management.service;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {
    List<TaskDto> getTasksForImplementationById(Long user_id);

    List<TaskDto> getCreatedTaskById(Long user_id);

    void changeStatus(Long task_id, String status) throws AccessDeniedException;

    List<TaskDto> getAllCreatedTasks();

    List<TaskDto> getAllTasksForImplementation();

    void saveNewUser(User user);
}
