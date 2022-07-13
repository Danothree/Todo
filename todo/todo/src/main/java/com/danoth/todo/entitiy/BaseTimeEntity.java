package com.danoth.todo.entitiy;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 매핑 정보만 상속받는 superclass 의미
@EntityListeners({AuditingEntityListener.class}) // LocalDateTime.now() 생성
@Getter
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
