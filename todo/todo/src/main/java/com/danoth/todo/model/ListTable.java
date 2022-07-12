package com.danoth.todo.model;

import com.danoth.todo.dto.ListTableDTO;
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

    public ListTable createTable(ListTable listTable){
        this.userId = listTable.getUserId();
        this.content = listTable.getContent();
        return this;
    }
}
