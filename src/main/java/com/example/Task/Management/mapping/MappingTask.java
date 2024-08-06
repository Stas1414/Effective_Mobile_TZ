package com.example.Task.Management.mapping;

import com.example.Task.Management.dto.TaskDto;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.reposiroty.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingTask {

    private TaskRepository taskRepository;

    private MappingComment mappingComment;

    private MappingUser mappingUser;

    @Autowired
    public MappingTask(TaskRepository taskRepository, MappingComment mappingComment, MappingUser mappingUser) {
        this.taskRepository = taskRepository;
        this.mappingComment = mappingComment;
        this.mappingUser = mappingUser;
    }

    public TaskDto mapToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setHeading(task.getHeading());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setPriority(task.getPriority());
        taskDto.setCreator(mappingUser.mapToUserDto(task.getCreator()));
        taskDto.setExecutor(mappingUser.mapToUserDto(task.getExecutor()));
        taskDto.setComments(mappingComment.mapToCommentDto(task.getComments()));
        return taskDto;
    }

    public Task mapToTask(TaskDto taskDto) {
        return taskRepository.findById(taskDto.getId()).orElseThrow(() -> new NullPointerException("The entity was not founded"));
    }
}
