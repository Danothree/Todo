package com.danoth.todo.service;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public List<Todo> retrieve(String userId) {
        return repository.findByUserId(userId);
    }


    public List<Todo> create(Todo todo) {
        //validate(todo);
        repository.save(todo);
        log.info("entity save ={} ", todo.getId());
        return retrieve(todo.getUserId());
    }

    public void delete(Long todoId) {
        repository.deleteById(todoId);
    }

    private void validate(Todo todo) {
        Assert.isNull(todo, "Entity is null");
        Assert.isNull(todo.getUserId(), "bad userId");
    }

}
