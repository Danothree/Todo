package com.danoth.todo.repository;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(String userId);


}
