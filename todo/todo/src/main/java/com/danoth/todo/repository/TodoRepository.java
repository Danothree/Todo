package com.danoth.todo.repository;

import com.danoth.todo.entitiy.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
