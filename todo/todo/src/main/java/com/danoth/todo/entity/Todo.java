package com.danoth.todo.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Table(name="todo")
@Entity @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Todo extends TimeEntity{

    @Id @Column(name="todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;

    private String title;

    private boolean success;

    public Todo setUserId(String userId){
        this.userId = userId;
        return this;
    }

    public void changeDone(){
        success = !success;
    }

    public void updateTodo(Todo todo){
        this.title = todo.getTitle();
        this.success = todo.isSuccess();
    }
}
