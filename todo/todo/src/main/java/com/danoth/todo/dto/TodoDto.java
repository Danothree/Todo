package com.danoth.todo.dto;

import com.danoth.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private boolean success;

    public TodoDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.success = todo.isSuccess();
    }

    public static Todo toEntity(TodoDto dto){
        return Todo.builder()
                .id(dto.getId())
                .success(dto.isSuccess())
                .title(dto.title)
                .build();
    }

}
