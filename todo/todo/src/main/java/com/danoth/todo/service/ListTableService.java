package com.danoth.todo.service;

import com.danoth.todo.dto.ListTableDTO;
import com.danoth.todo.model.ListTable;
import com.danoth.todo.repository.ListTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListTableService {

    private final ListTableRepository listTableRepository;

    public void save(ListTableDTO listTableDTO){
        ListTable listTable = new ListTable(listTableDTO);
        log.info("content = {}",listTableDTO.getContent());
        listTableRepository.save(listTable);
    }


    public List<ListTableDTO> getList(){
        List<ListTable> listTableList = listTableRepository.findAll();
        List<ListTableDTO> listTableDTOList = new ArrayList<>();
        listTableList.stream().forEach(list -> {
            ListTableDTO listTableDTO = new ListTableDTO(list);
            listTableDTOList.add(listTableDTO);
        });
        return listTableDTOList;
    }

    public void deleteTable(ListTableDTO listTableDTO) {
        listTableRepository.deleteByUserIdAndContent(listTableDTO.getUserId(),listTableDTO.getContent());
    }
}
