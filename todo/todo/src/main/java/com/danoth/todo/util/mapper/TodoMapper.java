package com.danoth.todo.util.mapper;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDto toDto(Todo todo);

   // @Mapping(target = "id", ignore = true)
    Todo toEntity(TodoDto dto);
}
