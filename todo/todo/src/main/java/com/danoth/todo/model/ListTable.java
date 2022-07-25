package com.danoth.todo.model;

import com.danoth.todo.dto.ListTableDTO;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class ListTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String content;

    private boolean completeCheck;

    @PrePersist
    public void prePersist(){
        this.completeCheck = false;
    }

    public ListTable createTable(ListTableDTO listTableDTO){
        this.userId = listTableDTO.getUserId();
        this.content = listTableDTO.getContent();
        return this;
    }
}
