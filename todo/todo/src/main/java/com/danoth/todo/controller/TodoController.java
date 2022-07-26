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
    public String todoList(){
        return "index";
    }

//    @PatchMapping(value = "/toDo/{content}")
//    public String updateTodoList(@PathVariable String content){
//        ListTable findListTable = listTableRepository.findById(table_id).get();
//        listTableRepository.save(findListTable);
//        return "redirect:/";
//    }


}
