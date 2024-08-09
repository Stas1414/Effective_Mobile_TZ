package com.example.Task.Management.service;

import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.TaskRepository;
import com.example.Task.Management.reposiroty.UserRepository;
import com.example.Task.Management.service.Impl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Array;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContext);
        Mockito.lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    public void deleteTaskTest() throws AccessDeniedException {
        Task task = new Task();
        task.setId(1L);

        User user = new User();
        user.setId(1L);
        user.setCreatedTask(new ArrayList<>(Arrays.asList(task)));
        user.setEmail("test@mail.ru");

        Mockito.when(authentication.getName()).thenReturn(user.getEmail());
        Mockito.when(userRepository.findByEmail(authentication.getName())).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        taskService.deleteTask(1L);

        Assertions.assertEquals(0, user.getCreatedTask().size());
    }

    @Test
    public void updateTaskTest() throws AccessDeniedException {
        Task task = new Task();
        task.setId(1L);

        Task updatedTask = new Task();
        updatedTask.setHeading("TestHeading");

        User user = new User();
        user.setId(1L);
        user.setCreatedTask(new ArrayList<>(Arrays.asList(task)));
        user.setEmail("test@mail.ru");

        Mockito.when(authentication.getName()).thenReturn(user.getEmail());
        Mockito.when(userRepository.findByEmail(authentication.getName())).thenReturn(user);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        taskService.updateTask(1L, updatedTask);

        Assertions.assertEquals("TestHeading", task.getHeading());
    }

}
