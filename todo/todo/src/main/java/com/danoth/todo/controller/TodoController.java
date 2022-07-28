package com.danoth.todo.controller;

import com.danoth.todo.repository.ListTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final ListTableRepository listTableRepository;

    @GetMapping
    public String todoList(){
        return "index";
    }
}
