package com.example.Task.Management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Task-Management",
description = "The Task Management API allows users to create, manage, and track tasks." +
        " The API provides functionality for user registration, authentication, task management, and more." +
        " With it, you can easily integrate the task management system into any application.",
contact = @Contact(name = "Stas",email = "Kolbovich.stas@mail.ru")))
public class OpenApiConfig {
}
