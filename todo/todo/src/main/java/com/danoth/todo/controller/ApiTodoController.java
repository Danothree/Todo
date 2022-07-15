package com.danoth.todo.controller;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ApiTodoController {
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
        service.update(TodoDto.toEntity(todoDto)
                .setUserId(temporaryUserId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{todoId}")
    public ResponseEntity changeDone(@PathVariable Long todoId){
        service.changeSuccess(todoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/completed")
    public ResponseEntity clearCompleted(){
        service.clearCompleted(temporaryUserId);
        return ResponseEntity.ok().build();
    }

}
