package com.danoth.todo.controller;

import com.danoth.todo.aop.annotation.Timer;
import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.service.TodoService;
import com.danoth.todo.util.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.danoth.todo.util.mapper.TodoMapper.INSTANCE;

@Timer
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ApiTodoController {
    private String temporaryUserId = "temporary_user";
    private final TodoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody TodoDto dto){
        service.create(INSTANCE.toEntity(dto)
                .setUserId(temporaryUserId));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody TodoDto dto){
        service.delete(dto.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TodoDto dto){
        service.update(INSTANCE.toEntity(dto)
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
