package com.danoth.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ListTableDTO {
    private String userId;
    private String content;
    private boolean completeCheck;
}
