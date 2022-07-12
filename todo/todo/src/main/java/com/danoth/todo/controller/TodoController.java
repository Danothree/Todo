package com.danoth.todo.controller;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private String temporaryUserId = "temporary_user";
    private final TodoService service;

    @GetMapping
    public String list(Model model){
        List<TodoDto> dtos = service.retrieve(temporaryUserId).stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());

        model.addAttribute("count", dtos.size());
        model.addAttribute("TodoList", dtos);
        model.addAttribute("TodoDto", new TodoDto());
        return "todo/todo";
    }

    public ResponseEntity create(Model model, TodoDto todoDto){
        List<TodoDto> dtos = service.create(TodoDto.toEntity(todoDto)
                        .setUserId(temporaryUserId)).stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().build();
    }
}
