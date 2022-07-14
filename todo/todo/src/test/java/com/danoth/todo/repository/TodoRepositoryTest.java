package com.danoth.todo.repository;

import com.danoth.todo.config.TestDatasourceConfig;
import com.danoth.todo.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestDatasourceConfig.class})
@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @PersistenceContext
    EntityManager em;

    public void clear(){
        em.flush();
        em.clear();
    }

}