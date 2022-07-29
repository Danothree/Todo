package com.danoth.todo.factory.todo;

import com.danoth.todo.entity.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoFactory {

    public static List<Todo> createTodoList(){
        List<Todo> todos = new ArrayList<>();
        for(int i =1; i<=5; i++){
            todos.add(Todo.builder()
                    .userId("user")
                    .success(true)
                    .title("title"+i)
                    .build());
        }
        return todos;
    }

    public static Todo createTodo(){
        return Todo.builder()
                .userId("user")
                .success(true)
                .title("title")
                .build();
    }
}
