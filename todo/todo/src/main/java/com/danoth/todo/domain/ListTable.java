package com.danoth.todo.domain;

import com.danoth.todo.dto.ListTableDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
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

    public void modifyList(ListTableDTO listTableDTO) {
        if (listTableDTO.getContent() != null) {
            this.content = listTableDTO.getContent();
        }
        if (listTableDTO.getCompleteCheck() != null) {
            this.completeCheck = listTableDTO.getCompleteCheck();
        }
    }

    @Builder
    public void setListTable(ListTableDTO listTableDTO) {
        this.id = listTableDTO.getId();
        this.userId = listTableDTO.getUserId();
        this.content = listTableDTO.getContent();
        this.completeCheck = listTableDTO.getCompleteCheck();
    }
}
