package com.danoth.todo.controller;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.factory.todo.TodoFactory;
import com.danoth.todo.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class ApiTodoControllerTest {

    @Mock
    TodoService service;

    @InjectMocks
    ApiTodoController controller;

    @Test
    @DisplayName("POST: Todo 생성 테스트")
    void createTest(){
        //given

        //when

        //then

    }

    private TodoDto createTodoDto(){
        return new TodoDto(TodoFactory.createTodo());
    }

}