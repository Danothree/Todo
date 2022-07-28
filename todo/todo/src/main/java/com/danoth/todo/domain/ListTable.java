package com.danoth.todo.domain;

import com.danoth.todo.dto.ListTableDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListTable extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String content;

    private String completeCheck;

    @PrePersist
    public void prePersist(){
        this.completeCheck = "false";
    }

    public ListTable(ListTableDTO listTableDTO){
        this.userId = listTableDTO.getUserId();
        this.content = listTableDTO.getContent();
        this.completeCheck = listTableDTO.getCompleteCheck();
    }

    public void modifyContent(String content) {
        this.content = content;
    }

    @Builder
    public void setListTable(ListTableDTO listTableDTO) {
        this.id = listTableDTO.getId();
        this.userId = listTableDTO.getUserId();
        this.content = listTableDTO.getContent();
        this.completeCheck = listTableDTO.getCompleteCheck();
    }
}
