package com.danoth.todo.service;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entitiy.Todo;
import com.danoth.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 추가
    public void create(Todo todo) {
        todoRepository.save(todo);
    }

    // 조회
    public List<Todo> getList() {
        return todoRepository.findAll();
    }

    // 삭제
    @Transactional // service단에서 걸기
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String content) {
        Todo todo = todoRepository.findById(id).orElseThrow(EntityExistsException::new);
        todo.setContent(content);
        todoRepository.save(todo);
    }

}
