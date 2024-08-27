# Task Manager Project

## Overview

This Task Manager project is a Spring Boot application that provides RESTful APIs for managing tasks, users, and authentication using JSON Web Tokens (JWT). The project implements secure user registration and login features with Spring Security and JWT-based authentication.

## Key Features

- **User Registration**: New users can register by providing their email, password, and other required details.
- **User Login**: Registered users can log in with their credentials to obtain a JWT token, which is required for accessing protected endpoints.
- **JWT Authentication**: Secure authentication using JWT tokens, allowing users to access protected resources after successful login.
- **Task Management**: APIs for creating, updating, and managing tasks (details on task endpoints would be included here).

## Technologies Used

- **Spring Boot**: To build the backend REST API.
- **Spring Security**: To secure the application and manage user authentication.
- **JWT (JSON Web Token)**: For securing API endpoints.
- **Hibernate & JPA**: To interact with the database.
- **MySQL**: As the database management system.

## JWT Authentication Workflow

1. **User Registration**: Users register by sending a POST request to `/api/auth/register` with their details.
2. **User Login**: Users log in by sending a POST request to `/api/auth/login`. Upon successful authentication, a JWT token is generated and returned to the client.
3. **Accessing Protected Resources**: The client includes the JWT token in the `Authorization` header (as a Bearer token) when making requests to protected endpoints.
   
