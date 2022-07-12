package com.danoth.todo.controller;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class TodoController {

    private final ListTableRepository listTableRepository;

    @GetMapping
    public List<ListTable> todoList(){
        List<ListTable> listTables = listTableRepository.findAll();
        return listTables;
    }

    @PostMapping
    public String saveTodoList(ListTable listTable){
        listTableRepository.save(listTable);
        return "redirect:/";
    }

    @PutMapping("/{table_id}")
    public String updateTodoList(@PathVariable("table_id") Long table_id, ListTable listTable){
        ListTable findListTable = listTableRepository.findById(table_id).get();
        listTableRepository.save(findListTable);
        return "redirect:/";
    }

    @DeleteMapping("/{table_id}")
    public String deleleTodoList(@PathVariable("table_id") Long table_id){
        listTableRepository.deleteById(table_id);
        return "redirect:/";
    }
}
