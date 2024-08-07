package com.example.Task.Management.service.Impl;


import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.TaskRepository;
import com.example.Task.Management.reposiroty.UserRepository;
import com.example.Task.Management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        task.setCreator(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void updateTask(Long task_id, Task task) throws AccessDeniedException {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Task> tasksList = user.getCreatedTask();
        for (Task usersTask : tasksList) {
            if (task_id == usersTask.getId()) {
                if (task.getExecutor() != null) {
                    usersTask.setExecutor(task.getExecutor());
                }
                if (task.getHeading() != null) {
                    usersTask.setHeading(task.getHeading());
                }
                if (task.getDescription() != null) {
                    usersTask.setDescription(task.getDescription());
                }
                if (task.getPriority() != null) {
                    usersTask.setPriority(task.getPriority());
                }
                taskRepository.save(usersTask);
                break;
            }
            else {
                throw new AccessDeniedException("You cant update this task");
            }
        }
    }

    @Override
    @Transactional
    public void deleteTask(Long task_id) throws AccessDeniedException {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Task> tasks = user.getCreatedTask();
        for (Task task : tasks) {
            if (task_id == task.getId()) {
                user.getCreatedTask().remove(task_id);
                userRepository.save(user);
                return;
            }
            else {
                throw new AccessDeniedException("You cant delete this task");
            }
        }
    }
}
