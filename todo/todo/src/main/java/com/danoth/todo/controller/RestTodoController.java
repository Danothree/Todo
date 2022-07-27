package com.danoth.todo.controller;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import com.danoth.todo.service.ListTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RestTodoController {

    private final ListTableService listTableService;
    private final ListTableRepository listTableRepository;

    @GetMapping("/toDo")
    public List<ListTableDTO> getList() {
        return listTableService.getList();
    }

    @PostMapping("/toDo")
    public void saveTodoList(@RequestBody ListTableDTO listTableDTO) {
        listTableService.save(listTableDTO);
    }

    @DeleteMapping("/toDo")
    public void deleteTodoList(ListTableDTO listTableDTO){
        listTableRepository.deleteByUserIdAndContent(listTableDTO.getUserId(),listTableDTO.getContent());
    }

}
