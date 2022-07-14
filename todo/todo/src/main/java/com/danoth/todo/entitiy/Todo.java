package com.danoth.todo.entitiy;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 생성자 생성
@Entity // DB에 테이블 생성
@Table(name = "todo")
public class Todo extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // 0 - false, 1 - true
    private boolean isCompleted;

    public Todo update(Todo todo) {
        this.content = todo.getContent();
        this.isCompleted = todo.isCompleted();
        return this;
    }
}
