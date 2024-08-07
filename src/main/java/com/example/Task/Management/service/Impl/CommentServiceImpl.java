package com.example.Task.Management.service.Impl;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.mapping.MappingComment;
import com.example.Task.Management.model.Comment;
import com.example.Task.Management.model.Task;
import com.example.Task.Management.model.User;
import com.example.Task.Management.reposiroty.CommentRepository;
import com.example.Task.Management.reposiroty.TaskRepository;
import com.example.Task.Management.reposiroty.UserRepository;
import com.example.Task.Management.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private MappingComment mappingComment;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository, UserRepository userRepository, MappingComment mappingComment) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.mappingComment = mappingComment;
    }

    @Override
    @Transactional
    public Comment createComment(Long task_id, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        Task task = taskRepository.findById(task_id).orElseThrow(() -> new NullPointerException("The task was not founded"));
        comment.setTask(task);
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllCommentsFromTask(Long task_id) {
        Task task = taskRepository.findById(task_id).orElseThrow(() -> new NullPointerException("The task was not founded"));
        return mappingComment.mapToCommentDto(task.getComments());
    }

    @Override
    @Transactional
    public void deleteComment(Long comment_id) throws AccessDeniedException {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Comment comment : user.getComments()) {
            if (comment.getId() == comment_id) {
                user.getComments().remove(comment_id);
                return;
            } else {
                throw new AccessDeniedException("You cant delete this comment");
            }
        }
    }

    @Override
    @Transactional
    public void updateComment(Long comment_id) {

    }

}
