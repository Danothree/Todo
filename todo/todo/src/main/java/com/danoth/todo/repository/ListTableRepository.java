package com.danoth.todo.repository;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.domain.ListTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListTableRepository extends JpaRepository<ListTable, Long> {

    void save(ListTableDTO listTableDTO);

    void deleteByUserIdAndContent(String userId,String content);

}
