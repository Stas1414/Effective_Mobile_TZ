package com.example.Task.Management.mapping;

import com.example.Task.Management.dto.CommentDto;
import com.example.Task.Management.model.Comment;
import com.example.Task.Management.reposiroty.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MappingComment {

    private CommentRepository commentRepository;

    private MappingUser mappingUser;




    @Autowired
    public MappingComment(CommentRepository commentRepository, MappingUser mappingUser) {
        this.commentRepository = commentRepository;
        this.mappingUser = mappingUser;
    }

    public List<CommentDto> mapToCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setContent(comment.getContent());
            commentDto.setAuthor(mappingUser.mapToUserDto(comment.getAuthor()));
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    public List<Comment> mapToComment(List<CommentDto> commentDtoList) {
        List<Comment> comments = new ArrayList<>();
        for (CommentDto commentDto : commentDtoList) {
            Optional<Comment> comment = commentRepository.findById(commentDto.getId());
            if (comment.isPresent()) {
                comments.add(comment.get());
            }
        }
        return comments;
    }
}
