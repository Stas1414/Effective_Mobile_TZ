package com.example.Task.Management.service.Impl;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.mapping.MappingTask;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.TaskRepository;
import com.example.Task.Management.reposiroty.UserRepository;
import com.example.Task.Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private MappingTask mappingTask;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, MappingTask mappingTask) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.mappingTask = mappingTask;
    }


    @Override
    public List<TaskDto> getTasksForImplementationById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("The user was not founded"));
        return mapToList(user.getTasksForImplementation());
    }

    @Override
    public List<TaskDto> getCreatedTaskById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("The user was not founded"));
        return mapToList(user.getCreatedTask());
    }

    @Override
    @Transactional
    public void changeStatus(Long taskId, String status) throws AccessDeniedException {
        Task usersTask = taskRepository.findById(taskId).orElseThrow(() -> new NullPointerException("The task was not founded"));
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Task task : user.getTasksForImplementation()) {
            if (Objects.equals(usersTask.getId(), task.getId())) {
                task.setStatus(status);
                taskRepository.save(task);
            } else {
                throw new AccessDeniedException("You cant change status");
            }
        }
    }

    @Override
    public List<TaskDto> getAllCreatedTasks() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapToList(user.getCreatedTask());
    }

    @Override
    public List<TaskDto> getAllTasksForImplementation() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapToList(user.getTasksForImplementation());
    }

    @Override
    public void saveNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    private List<TaskDto> mapToList(List<Task> usersTasks) {
        List<TaskDto> tasks = new ArrayList<>();
        for (Task task : usersTasks) {
            tasks.add(mappingTask.mapToTaskDto(task));
        }
        return tasks;
    }
}
