package com.danoth.todo.controller;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoRestController {
    private String temporaryUserId = "temporary_user";
    private final TodoService service;

    @PostMapping
    public ResponseEntity create(TodoDto todoDto){
        service.create(TodoDto.toEntity(todoDto)
                .setUserId(temporaryUserId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(Long todoId){
        service.delete(todoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(TodoDto todoDto){
        service.update(TodoDto.toEntity(todoDto));
        return ResponseEntity.ok().build();
    }
}
