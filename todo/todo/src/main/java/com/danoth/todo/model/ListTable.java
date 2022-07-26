package com.danoth.todo.model;

import com.danoth.todo.dto.ListTableDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String content;

    private boolean completeCheck;

    @PrePersist
    public void prePersist(){
        this.completeCheck = false;
    }

    public ListTable(ListTableDTO listTableDTO){
        this.userId = listTableDTO.getUserId();
        this.content = listTableDTO.getContent();
    }
}
