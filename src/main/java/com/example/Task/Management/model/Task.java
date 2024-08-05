package com.example.Task.Management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heading;

    private String description;

    private String status;

    private String priority;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User executor;

}
