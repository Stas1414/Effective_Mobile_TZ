package com.example.Task.Management.service;


import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.mapping.MappingTask;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.TaskRepository;
import com.example.Task.Management.reposiroty.UserRepository;
import com.example.Task.Management.service.Impl.UserServiceImpl;
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

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private MappingTask mappingTask;

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
    public void changeStatusTest() throws AccessDeniedException {
        Task task = new Task();
        task.setId(1L);

        User user = new User();
        user.setEmail("test@mail.ru");
        user.setTasksForImplementation(List.of(task));

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(securityContext.getAuthentication().getName()).thenReturn(user.getEmail());

        userService.changeStatus(1L, "Выполнено!");
        Assertions.assertEquals("Выполнено!", task.getStatus());
    }

    @Test
    public void getTasksForImplementationByIdTest() {
        User user = new User();
        user.setId(1L);

        Task task = new Task();
        task.setId(1L);


        user.setTasksForImplementation(List.of(task));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        List<TaskDto> result = userService.getTasksForImplementationById(1L);

        Assertions.assertEquals(1,result.size());
    }


}
