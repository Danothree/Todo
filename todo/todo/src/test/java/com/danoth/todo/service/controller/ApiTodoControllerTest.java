package com.danoth.todo.service.controller;

import com.danoth.todo.controller.ApiTodoController;
import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.factory.todo.TodoFactory;
import com.danoth.todo.service.TodoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class ApiTodoControllerTest {

    @Mock
    TodoService service;

    @InjectMocks
    ApiTodoController controller;

    private MockMvc mockMvc;
    private String basicUrl = "/api/todos";

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("POST: Todo 생성 테스트")
    void createTodoTest() throws Exception {
        //given, when, then
        mockMvc.perform(
                post(basicUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createTodoDto()))
        ).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE : Todo 제거 테스트")
    void deleteTodoTest() throws Exception{
        // given, when, then
        mockMvc.perform(
                delete(basicUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createTodoDto()))
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT : Todo 수정 테스트")
    void updateTodoTest() throws Exception{
        // given, when, then
        mockMvc.perform(
                put(basicUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(createTodoDto()))
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Success 변경 테스트")
    void changeDoneTest() throws Exception{
        // given, when, then
        mockMvc.perform(put(basicUrl + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete all completed lists 테스트")
    void clearCompletedTest() throws Exception {
        // given, when, then
        mockMvc.perform(delete(basicUrl + "/completed"))
                .andExpect(status().isOk());
    }

    private TodoDto createTodoDto(){
        return new TodoDto(TodoFactory.createTodo());
    }

}