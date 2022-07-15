package com.danoth.todo.repository;

import com.danoth.todo.entitiy.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long id);
}
