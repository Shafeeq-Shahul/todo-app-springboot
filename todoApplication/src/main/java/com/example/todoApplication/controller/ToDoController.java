package com.example.todoApplication.controller;

import com.example.todoApplication.dto.ApiResponse;
import com.example.todoApplication.dto.ToDoDTO;
import com.example.todoApplication.mapper.ToDoMapper;
import com.example.todoApplication.model.ToDo;
import com.example.todoApplication.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("todo")
public class ToDoController {

    private final ToDoService toDoService;
    private final ToDoMapper toDoMapper;

    @Autowired
    public ToDoController(ToDoService toDoService, ToDoMapper toDoMapper) {
        this.toDoService = toDoService;
        this.toDoMapper = toDoMapper;
    }

    @GetMapping("/getAllToDos")
    public ResponseEntity<ApiResponse<List<ToDoDTO>>> getAllToDos() {
        List<ToDoDTO> toDoDTOList = toDoService.getAllTodos()
                .stream()
                .map(toDoMapper::toDTO)
                .toList();
        ApiResponse<List<ToDoDTO>> apiResponse = new ApiResponse<>("Data retrieved successfully", toDoDTOList);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getCompletedToDos")
    public ResponseEntity<ApiResponse<List<ToDoDTO>>> getCompletedToDos() {
        List<ToDo> completedToDos = toDoService.getCompletedToDos();
        List<ToDoDTO> completedToDoDTOs =  completedToDos
                .stream()
                .map(toDoMapper::toDTO)
                .toList();
        ApiResponse<List<ToDoDTO>> apiResponse = new ApiResponse<>("Data retrieved successfully", completedToDoDTOs);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getToDo")
    public ResponseEntity<ApiResponse<ToDoDTO>> getToDo(@RequestParam Long id) {
        ToDoDTO toDoDTO = toDoMapper.toDTO(toDoService.getTodoById(id));
        ApiResponse<ToDoDTO> apiResponse = new ApiResponse<>("Data retrieved successfully", toDoDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/saveToDo")
    public ResponseEntity<ApiResponse<ToDoDTO>> saveToDo(@RequestBody ToDoDTO toDoDTO) {
        ToDo toDoEntity = toDoService.createTodo(toDoMapper.toEntity(toDoDTO));
        ApiResponse<ToDoDTO> apiResponse = new ApiResponse<>("Data saved successfully", toDoMapper.toDTO(toDoEntity));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateToDo")
    public ResponseEntity<ApiResponse<ToDoDTO>> updateToDo(@RequestParam Long id, @RequestBody ToDoDTO toDoDTO) {
        ToDo toDoEntity = toDoService.updateTodo(id,toDoMapper.toEntity(toDoDTO));
        ApiResponse<ToDoDTO> apiResponse = new ApiResponse<>("Data updated successfully", toDoMapper.toDTO(toDoEntity));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<ApiResponse<ToDoDTO>> completeToDo(@PathVariable Long id) {
        ToDo toDo = toDoService.getTodoById(id);
        toDo.setCompleted(true);
        ToDo updated = toDoService.updateTodo(id, toDo);
        ToDoDTO updatedDTO = toDoMapper.toDTO(updated);
        ApiResponse<ToDoDTO> apiResponse = new ApiResponse<>("ToDo completed", updatedDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteToDo")
    public ResponseEntity<ApiResponse<?>> deleteToDo(@RequestParam Long id) {
        toDoService.deleteTodo(id);
        ApiResponse<?> apiResponse = new ApiResponse<>("Data deleted successfully", null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/deleteAllToDos")
    public ResponseEntity<ApiResponse<?>> deleteAllToDo() {
        toDoService.deleteAllTodo();
        ApiResponse<?> apiResponse = new ApiResponse<>("Data deleted successfully", null);
        return ResponseEntity.ok(apiResponse);
    }
}
