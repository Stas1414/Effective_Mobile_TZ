package com.example.Task.Management.controller;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.service.Impl.TaskServiceImpl;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void createTaskTest() throws Exception {
        Task task = new Task();
        task.setHeading("Task 1");
        task.setDescription("Description 1");

        Mockito.doNothing().when(taskService).createTask(Mockito.any(Task.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"heading\":\"Task 1\",\"description\":\"Description 1\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateTaskTest() throws Exception {
        Long taskId = 1L;

        Mockito.doNothing().when(taskService).updateTask(Mockito.any(Long.class), Mockito.any(Task.class));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/tasks/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"heading\":\"Task 1\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        Long taskId = 1L;

        Mockito.doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/{taskId}",taskId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTaskAccessDenied() throws Exception {
        Long taskId = 1L;

        Mockito.doThrow(new AccessDeniedException("Forbidden")).when(taskService).deleteTask(taskId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/{taskId}", taskId))
                .andExpect(status().isForbidden());
    }
}
