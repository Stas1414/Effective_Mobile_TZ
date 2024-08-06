package com.example.Task.Management.reposiroty;

import com.example.Task.Management.model.Comment;
import com.example.Task.Management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByTask(Task task);
}
