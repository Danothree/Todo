package com.danoth.todo.dto;

import com.danoth.todo.domain.ListTable;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTableDTO {

    private Long id;

    @NotBlank(message = "id를 입력하세요")
    private String userId;

    @NotBlank(message = "content를 입력하세요")
    private String content;

    private String completeCheck;

    public ListTableDTO(ListTable listTable){
        this.id = listTable.getId();
        this.userId = listTable.getUserId();
        this.content = listTable.getContent();
        this.completeCheck = listTable.getCompleteCheck();
    }
}
