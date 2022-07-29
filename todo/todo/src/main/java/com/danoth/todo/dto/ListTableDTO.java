package com.danoth.todo.dto;

import com.danoth.todo.domain.ListTable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTableDTO {

    private Long id;
    
    private String userId;
    private String content;
    private String completeCheck;

    public ListTableDTO(ListTable listTable){
        this.id = listTable.getId();
        this.userId = listTable.getUserId();
        this.content = listTable.getContent();
        this.completeCheck = listTable.getCompleteCheck();
    }
}
