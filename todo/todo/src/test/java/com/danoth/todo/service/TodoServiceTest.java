package com.danoth.todo.service;

import com.danoth.todo.entity.Todo;
import com.danoth.todo.exception.InvalidUserIdException;
import com.danoth.todo.exception.TitleException;
import com.danoth.todo.factory.todo.TodoFactory;
import com.danoth.todo.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService service;

    @Mock
    private TodoRepository repository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("유저 TodoList 조회 테스트")
    void retrieveTest() {
        //given  (userId = user)
        List<Todo> todoList = TodoFactory.createTodoList();
        given(repository.findByUserId(anyString(), any())).willReturn(todoList);

        //when
        List<Todo> findTodoList = service.retrieve("user");

        //then
        assertThat(findTodoList.get(0).getUserId()).isEqualTo("user");
    }

    @Test
    @DisplayName("Todo 저장 테스트")
    void todoSaveTest() {
        //given
        Todo todo = TodoFactory.createTodo();
        given(repository.save(todo)).willReturn(any());

        //when
        service.create(todo);

        //then
        verify(repository).save(todo);
    }

    @Test
    @DisplayName("타이틀 검증 및 오류 메시지 테스트")
    void validateTitleTest(){
        //given
        Todo todo = Todo.builder()
                .userId("user")
                .success(false)
                .build();

        //when, then
        String error = assertThrows(TitleException.class, () -> {
            service.create(todo);
        }).getMessage();
        assertThat(error).isEqualTo("The input value of the title is invalid.");
    }
    @Test
    @DisplayName("user Id 검증 및 오류 메시지 테스트")
    void validateUserIdTest(){
        //given
        Todo todo = Todo.builder()
                .success(false)
                .title("title")
                .build();

        //when, then
        String error = assertThrows(InvalidUserIdException.class, () -> {
            service.create(todo);
        }).getMessage();
        assertThat(error).isEqualTo("Invalid userId value");
    }
    @Test
    @DisplayName("Todo 삭제 테스트")
    void deleteTest() {
        //given
        service.delete(1L);

        //when
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Todo 완료,미완 변경 로직 테스트")
    void changeSuccessTest() {
        //given
        Todo todo = TodoFactory.createTodo();
        given(repository.findById(1L)).willReturn(Optional.of(todo));

        //when
        service.changeSuccess(1L);

        //then
        verify(repository).findById(1L);
        assertThat(todo.isSuccess()).isFalse();
    }

    @Test
    @DisplayName("Todo 수정 로직 테스트")
    void updateTest() {
        //given
        Todo todo = TodoFactory.createTodo();
        given(repository.findById(1L))
                .willReturn(Optional.of(todo));

        //when
       service.update(Todo.builder()
                           .id(1L)
                           .title("title")
                           .userId("user")
                           .success(false)
                           .build());
        //then
        assertThat(todo.isSuccess()).isFalse();
    }

    @Test
    @DisplayName("Active List 조회 테스트")
    void getActiveListTest() {
        //given
        Todo todo = Todo.builder()
                .userId("user")
                .title("title")
                .success(false)
                .build();

        given(repository.findByUserIdAndSuccess("user", false))
                .willReturn(Arrays.asList(todo));

        //when
        List<Todo> todos = service.activeList(todo.getUserId());

        //then
        assertThat(todos.get(0).isSuccess()).isFalse();
    }

    @Test
    @DisplayName("completed List 조회 테스트")
    void getCompletedListTest() {
        //given
        Todo todo = Todo.builder()
                .userId("user")
                .title("title")
                .success(true)
                .build();

        given(repository.findByUserIdAndSuccess("user", true))
                .willReturn(Arrays.asList(todo));

        //when
        List<Todo> todos = service.completedList(todo.getUserId());

        //then
        assertThat(todos.get(0).isSuccess()).isTrue();
    }
}