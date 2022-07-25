package com.danoth.todo.controller;

import com.danoth.todo.aop.annotation.Timer;
import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Timer
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

    @GetMapping("/active")
    public String activeList(Model model){
        List<TodoDto> dtos = service.activeList(temporaryUserId).stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());

        model.addAttribute("count", dtos.size());
        model.addAttribute("TodoList", dtos);
        model.addAttribute("TodoDto", new TodoDto());
        return "todo/todo";
    }

    @GetMapping("/completed")
    public String completeList(Model model){
        List<TodoDto> dtos = service.completedList(temporaryUserId).stream()
                .map(TodoDto::new)
                .collect(Collectors.toList());

        model.addAttribute("count", dtos.size());
        model.addAttribute("TodoList", dtos);
        model.addAttribute("TodoDto", new TodoDto());
        return "todo/todo";
    }
}
