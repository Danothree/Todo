package com.danoth.todo.factory.todo;

import com.danoth.todo.entity.Todo;

import java.util.Arrays;
import java.util.List;

public class TodoFactory {

    public static List<Todo> createTodoList(){
        return Arrays.asList(Todo.builder()
                .userId("user")
                .success(true)
                .title("title")
                .build());
    }
    public static Todo createTodo(){
        return Todo.builder()
                .userId("user")
                .success(true)
                .title("title")
                .build();
    }
}
