package com.example.todoApplication.service;

import com.example.todoApplication.model.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> getAllTodos();
    List<ToDo> getCompletedToDos();
    ToDo getTodoById(Long id);
    ToDo createTodo(ToDo todo);
    ToDo updateTodo(Long id, ToDo todo);
    void deleteTodo(Long id);
    void deleteAllTodo();
}
