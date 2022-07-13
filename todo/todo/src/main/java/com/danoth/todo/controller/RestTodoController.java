package com.danoth.todo.controller;

import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestTodoController {

    private final ListTableRepository listTableRepository;

    @PostMapping("")
    public String saveTodoList(ListTable listTable){
        listTableRepository.save(listTable);
        return "redirect:/";
    }

}
