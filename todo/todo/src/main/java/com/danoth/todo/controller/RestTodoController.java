package com.danoth.todo.controller;

import com.danoth.todo.domain.ListTable;
import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.repository.ListTableRepository;
import com.danoth.todo.service.ListTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RestTodoController {

    private final ListTableService listTableService;
    private final ListTableRepository listTableRepository;

    @GetMapping("/todo")
    public List<ListTableDTO> getList() {
        return listTableService.getList();
    }

    @PostMapping("/todo")
    public void saveTodoList(@RequestBody ListTableDTO listTableDTO) {
        listTableService.save(listTableDTO);
    }

    @PatchMapping("/todo/{id}/{content}")
    public void updateTodoList(@PathVariable Long id,@RequestParam String content) {
        ListTable listTable = listTableRepository.findById(id).get();
        listTable.modifyContent(content);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodoList(@PathVariable Long id) {
        listTableRepository.deleteById(id);
    }

}
