package com.danoth.todo.service;

import com.danoth.todo.entitiy.Todo;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 추가
    public void create(Todo todo) {
//        Todolist todolist = new Todolist();
//        todolist.setTodolistContent(todolistContent);
//        todolist.setTodolistCompleted(false);
//        todolist.setCreatedAt(LocalDateTime.now());
//        todolist.setUpdatedAt(LocalDateTime.now());
        todoRepository.save(todo);
    }

    // 조회
    public List<Todo> getList() {
        return todoRepository.findAll();
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

}
