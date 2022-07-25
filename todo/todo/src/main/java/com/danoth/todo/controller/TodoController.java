package com.danoth.todo.controller;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final ListTableRepository listTableRepository;

    @GetMapping
    public String todoList(Model model){
        model.addAttribute("listTableDto",new ListTableDTO());
        model.addAttribute("listTable",listTableRepository.findAll());
        return "index";
    }

    @PutMapping(value = "/{table_id}")
    public String updateTodoList(@PathVariable("table_id") Long table_id,@RequestBody ListTable listTable){
        ListTable findListTable = listTableRepository.findById(table_id).get();
        listTableRepository.save(findListTable);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public void deleleTodoList(@PathVariable Long id){
        listTableRepository.deleteById(id);
    }

}
