package com.danoth.todo.controller;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import com.danoth.todo.service.ListTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestTodoController {

    private final ListTableService listTableService;

    @PostMapping
    public void saveTodoList(ListTableDTO listTableDTO){
        listTableService.save(listTableDTO);
    }

}
