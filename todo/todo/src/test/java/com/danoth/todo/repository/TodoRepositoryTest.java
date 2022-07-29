package com.danoth.todo.repository;

import com.danoth.todo.config.TestDatasourceConfig;
import com.danoth.todo.entity.Todo;
import com.danoth.todo.factory.todo.TodoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Todo 추가 테스트")
    void addTodoTest(){
        //given
        Todo todo = TodoFactory.createTodo();

        //when
        Todo saveTodo = repository.save(todo);

        //then
        assertThat(saveTodo.getTitle()).isEqualTo(todo.getTitle());
        assertThat(saveTodo.getUserId()).isEqualTo(todo.getUserId());
        assertThat(saveTodo.isSuccess()).isEqualTo(todo.isSuccess());
    }

    @Test
    @DisplayName("User Todo 목록 조회 테스트")
    void findByUserIdTest(){
        //given
        repository.saveAll(TodoFactory.createTodoList());
        clear();

        //when
        List<Todo> findTodos = repository.findByUserId("user", Sort.by(Sort.Direction.ASC, "success"));

        //then
        assertThat(findTodos.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("UserId 와 Success 된 목록 조회 테스트")
    void findByUserIdAndSuccessTest(){
        //given
        repository.saveAll(successTodos());
        clear();

        //when
        List<Todo> findTodos = repository.findByUserIdAndSuccess("user", true);

        //then
        assertThat(findTodos.get(0).isSuccess()).isTrue();
        assertThat(findTodos.size()).isEqualTo(failureTodos().size());
    }

    @Test
    @DisplayName("UserId 와 Failure 된 목록 조회 테스트")
    void findByUserIdAndFailureTest(){
        //given
        repository.saveAll(failureTodos());
        clear();
        //when
        List<Todo> findTodos = repository.findByUserIdAndSuccess("user", false);

        //then
        assertThat(findTodos.get(0).isSuccess()).isFalse();
        assertThat(findTodos.size()).isEqualTo(failureTodos().size());
    }

    @Test
    @DisplayName("완료된 목록 전부 제거 테스트")
    void completedAllDeleteTest(){
        //given
        repository.saveAll(successTodos());
        clear();

        //when
        repository.deleteByUserIdAndSuccessIsTrue("user");
        List<Todo> findTodos = repository.findByUserId("user");

        //then
        assertThat(findTodos.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("userId 검증 성공 테스트")
    void validateUserIdSuccessTest(){
        //given
        Todo todo = TodoFactory.createTodo();
        repository.save(todo);
        clear();

        //when, then
        assertThat(repository.existsByUserId(todo.getUserId()))
                .isTrue();
    }

    @Test
    @DisplayName("userId 검증 실패 테스트")
    void validateUserIdFailureTest(){
        //given

        //given, when, then
        assertThat(repository.existsByUserId("user"))
                .isFalse();
    }

    private List<Todo> successTodos(){
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

    private List<Todo> failureTodos(){
        List<Todo> todos = new ArrayList<>();
        for(int i =1; i<=5; i++){
            todos.add(Todo.builder()
                    .userId("user")
                    .success(false)
                    .title("title"+i)
                    .build());
        }
        return todos;
    }

    public void clear(){
        em.flush();
        em.clear();
    }

}