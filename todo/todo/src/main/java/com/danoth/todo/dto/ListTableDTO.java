package com.danoth.todo.dto;

import com.danoth.todo.model.ListTable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTableDTO {
    private String userId;
    private String content;
    private boolean completeCheck;

    public ListTableDTO(ListTable listTable){
        this.userId = listTable.getUserId();
        this.content = listTable.getContent();
        this.completeCheck = listTable.isCompleteCheck();
    }
}
