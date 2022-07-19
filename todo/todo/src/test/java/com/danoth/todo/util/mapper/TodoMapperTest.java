package com.danoth.todo.util.mapper;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TodoMapperTest {

    @Test
    @DisplayName("Todo -> TodoDto mapper 테스트")
    void shouldMapEntityToDto(){
        //given
        Todo todo = createTodo();

        //when
        TodoDto todoDto = TodoMapper.INSTANCE.toDto(todo);

        //then
        assertThat(todoDto.getId()).isEqualTo(todo.getId());
        assertThat(todoDto.getTitle()).isEqualTo(todo.getTitle());
        assertThat(todoDto.isSuccess()).isEqualTo(todo.isSuccess());
    }

    @Test
    @DisplayName("TodoDto -> Todo mapper 테스트")
    void shouldMapDtoToEntity(){
        //given
        TodoDto dto = new TodoDto(createTodo());

        //when
        Todo todo = TodoMapper.INSTANCE.toEntity(dto);

        //then
        assertThat(todo.getId()).isEqualTo(1L);
        assertThat(todo.getTitle()).isEqualTo("title");
        assertThat(todo.isSuccess()).isTrue();
    }

    private Todo createTodo(){
        return Todo.builder()
                .id(1L)
                .title("title")
                .success(true)
                .userId("user")
                .build();
    }
}