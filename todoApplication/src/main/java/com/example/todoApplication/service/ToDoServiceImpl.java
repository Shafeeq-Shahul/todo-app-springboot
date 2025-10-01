package com.example.todoApplication.service;

import com.example.todoApplication.exception.ResourceNotFoundException;
import com.example.todoApplication.model.ToDo;
import com.example.todoApplication.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getCompletedToDos() {
        return toDoRepository.findByCompleted(true);
    }

    @Override
    public ToDo getTodoById(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found"));
    }

    @Override
    public ToDo createTodo(ToDo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        return toDoRepository.save(todo);
    }

    @Override
    public ToDo updateTodo(Long id, ToDo todo) {
        if (!toDoRepository.existsById(id)) {
            throw new ResourceNotFoundException("ToDo not found");
        }
        todo.setTitle(todo.getTitle());
        todo.setDescription(todo.getDescription());
        todo.setCompleted(todo.isCompleted());
        todo.setUpdatedAt(LocalDateTime.now());
        return toDoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        if (!toDoRepository.existsById(id)) {
            throw new ResourceNotFoundException("todo not found");
        }
        toDoRepository.deleteById(id);
    }

    @Override
    public void deleteAllTodo() {
        toDoRepository.deleteAll();
    }
}
