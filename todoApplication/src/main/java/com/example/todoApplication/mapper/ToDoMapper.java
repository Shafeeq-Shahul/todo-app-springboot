package com.example.todoApplication.mapper;

import com.example.todoApplication.dto.ToDoDTO;
import com.example.todoApplication.model.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper {

    public ToDo toEntity(ToDoDTO toDoDTO) {
        ToDo entity = new ToDo();
        entity.setId(toDoDTO.getId());
        entity.setTitle(toDoDTO.getTitle());
        entity.setDescription(toDoDTO.getDescription());
        entity.setCompleted(toDoDTO.isCompleted());
        entity.setCreatedAt(toDoDTO.getCreatedAt());
        entity.setUpdatedAt(toDoDTO.getUpdatedAt());
        return entity;
    }

    public ToDoDTO toDTO(ToDo toDo) {
        ToDoDTO dto = new ToDoDTO();
        dto.setId(toDo.getId());
        dto.setTitle(toDo.getTitle());
        dto.setDescription(toDo.getDescription());
        dto.setCompleted(toDo.isCompleted());
        dto.setCreatedAt(toDo.getCreatedAt());
        dto.setUpdatedAt(toDo.getUpdatedAt());
        return dto;
    }
}
