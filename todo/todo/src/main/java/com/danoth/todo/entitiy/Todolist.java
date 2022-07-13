package com.danoth.todo.entitiy;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Builder // 객체 생성시 값 세팅, 생성자 변수의 개수가 변할 수 있으므로 빌더패턴을 써주는 게 좋다
@Getter // setter는 사용금지
//@Data
@NoArgsConstructor // 기본 생성자 생성
@Entity // DB에 테이블 생성
@Table(name = "todolist")
public class Todolist extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todolistContent;

    // 0 - false, 1 - true
    private boolean todolistCompleted;

    @Builder // 객체 생성시 값 세팅, 생성자 변수의 개수가 변할 수 있으므로 빌더패턴을 써주는 게 좋다
    public Todolist(Long id, String todolistContent, boolean todolistCompleted) {
        this.id = id;
        this.todolistContent = todolistContent;
        this.todolistCompleted = todolistCompleted;
    }

    public Todolist update(Todolist todolist) {
        this.todolistContent = todolist.getTodolistContent();
        this.todolistCompleted = todolist.isTodolistCompleted();
        return this;
    }
    
}
