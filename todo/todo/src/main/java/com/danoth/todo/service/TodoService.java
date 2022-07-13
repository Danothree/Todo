package com.danoth.todo.service;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityExistsException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    @Transactional(readOnly = true)
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

    public void changeDone(Long todoId) {
        repository.findById(todoId)
                .orElseThrow(EntityExistsException::new)
                .changeDone();
    }

    public void update(Todo todo) {
        Todo entity = repository.findById(todo.getId())
                .orElseThrow(EntityExistsException::new);
        entity.updateTodo(todo);
    }

    private void validate(Todo todo) {
        Assert.isNull(todo, "Entity is null");
        Assert.isNull(todo.getUserId(), "bad userId");
    }


}
