package com.danoth.todo.service;

import com.danoth.todo.entitiy.Todolist;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

    public void create(String todolistContent) {
        Todolist todolist = new Todolist();
        todolist.setTodolistContent(todolistContent);
        todolist.setTodolistCompleted(false);
        todolist.setCreatedAt(LocalDateTime.now());
        todolist.setUpdatedAt(LocalDateTime.now());
        todoRepository.save(todolist);
    }

    public List<Todolist> getList() {
        return todoRepository.findAll();
    }
}
