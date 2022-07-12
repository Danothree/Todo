package com.danoth.todo.repository;

import com.danoth.todo.model.ListTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListTableRepository extends JpaRepository<ListTable, Long> {

}
