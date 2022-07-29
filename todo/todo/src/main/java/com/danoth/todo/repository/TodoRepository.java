package com.danoth.todo.repository;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(String userId, Sort sort);

    List<Todo> findByUserId(String userId);

    List<Todo> findByUserIdAndSuccess(String userId, boolean success);

    Boolean existsByUserId(String userId);

    @Query
    void deleteByUserIdAndSuccessIsTrue(String userId);
}
