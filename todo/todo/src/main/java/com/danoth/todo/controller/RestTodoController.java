package com.danoth.todo.controller;

import com.danoth.todo.domain.ListTable;
import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.repository.ListTableRepository;
import com.danoth.todo.service.ListTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestTodoController {

    private final ListTableService listTableService;
    private final ListTableRepository listTableRepository;

    @GetMapping("/todo")
    public ResponseEntity<List<ListTableDTO>> getList() {
        List<ListTableDTO> resultList = listTableService.getList();
        log.info("ToDo List = {}", resultList);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity saveTodoList(@RequestBody ListTableDTO listTableDTO) {
        listTableService.save(listTableDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity updateTodoList(@PathVariable Long id,@RequestBody ListTableDTO listTableDTO) {
        listTableService.update(id,listTableDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity deleteTodoList(@PathVariable Long id) {
        listTableRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
