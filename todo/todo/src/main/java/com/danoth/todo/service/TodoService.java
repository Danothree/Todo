package com.danoth.todo.service;

import com.danoth.todo.entity.Todo;
import com.danoth.todo.exception.InvalidUserIdException;
import com.danoth.todo.exception.TitleException;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    @Transactional(readOnly = true)
    public List<Todo> retrieve(String userId) {
        return repository.findByUserId(userId, Sort.by(Sort.Direction.ASC, "success"));
    }

    public List<Todo> create(Todo todo) {
        validate(todo);
        repository.save(todo);
        return retrieve(todo.getUserId());
    }

    public void delete(Long todoId) {
        repository.deleteById(todoId);
    }

    public void changeSuccess(Long todoId) {
        repository.findById(todoId)
                .orElseThrow(EntityExistsException::new)
                .changeDone();
    }

    public void update(Todo todo) {
        validate(todo);
        repository.findById(todo.getId())
                .orElseThrow(EntityExistsException::new)
                .updateTodo(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> activeList(String userId) {
        validateUserId(userId);
        return repository.findByUserIdAndSuccess(userId, false);
    }

    @Transactional(readOnly = true)
    public List<Todo> completedList(String userId) {
        validateUserId(userId);
        return repository.findByUserIdAndSuccess(userId, true);
    }

    public void clearCompleted(String userId) {
        validateUserId(userId);
        repository.deleteByUserIdAndSuccessIsTrue(userId);
    }

    private void validate(Todo todo) {
        validateTitle(todo);
        validateUser(todo);
    }

    private void validateTitle(Todo todo){
        if(!StringUtils.hasText(todo.getTitle())){
            throw new TitleException("The input value of the title is invalid.");
        }
    }

    private void validateUser(Todo todo){
        if(!StringUtils.hasText(todo.getUserId())){
            throw new InvalidUserIdException("Invalid userId value");
        }
    }

    private void validateUserId(String userId){
        if(!repository.existsByUserId(userId)){
            throw new EntityNotFoundException("UserId not find");
        }
    }
}
