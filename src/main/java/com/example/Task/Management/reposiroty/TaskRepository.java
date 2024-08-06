package com.example.Task.Management.reposiroty;


import com.example.Task.Management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByHeading(String heading);
}
