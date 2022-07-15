package com.danoth.todo.dto;

import com.danoth.todo.entitiy.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TodoDto {

    private Long id;
    private String content;
    private boolean isCompleted;

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.isCompleted();
    }

    public static Todo toEntity(TodoDto dto) {
        return Todo.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isCompleted(dto.isCompleted())
                .build();
    }


//    public Todo update(Todo todo) {
//        this.content = todo.getContent();
//        this.isCompleted = todo.isCompleted();
//        return this;
//    }

    public TodoDto update(TodoDto dto) {
        this.content = dto.getContent();
        this.isCompleted = dto.isCompleted();
        return this;
    }
}
