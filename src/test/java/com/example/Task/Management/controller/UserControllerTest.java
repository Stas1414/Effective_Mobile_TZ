package com.example.Task.Management.controller;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.mapping.MappingTask;
import com.example.Task.Management.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllCreatedTasksTest() throws Exception {
        List<TaskDto> tasks = Arrays.asList(new TaskDto(),
                new TaskDto());

        Mockito.when(userService.getAllCreatedTasks()).thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/created"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCreatedTaskByIdTest() throws Exception {
        Long userId = 1L;
        List<TaskDto> tasks = List.of(new TaskDto());
        Mockito.when(userService.getCreatedTaskById(userId)).thenReturn(tasks);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}/created", userId))
                .andExpect(status().isOk());
    }

    @Test
    public void changeStatusTest() throws Exception {
        Long taskId = 1L;
        String status = "IN_PROGRESS";

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/{taskId}", taskId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(status))
                .andExpect(status().isNoContent());
    }

    @Test
    public void changeStatusAccessDeniedTest() throws Exception {
        Long taskId = 1L;
        String status = "IN_PROGRESS";

        Mockito.doThrow(new AccessDeniedException("Forbidden")).when(userService).changeStatus(taskId, status);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/{taskId}", taskId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(status))
                .andExpect(status().isForbidden());
    }

}
