package com.example.Task.Management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "executor")
    private List<Task> tasksForImplementation;

    @OneToMany(mappedBy = "creator")
    private List<Task> createdTask;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

}
