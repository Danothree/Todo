package com.danoth.todo.repository;

import com.danoth.todo.entitiy.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todolist, Long> {
}
