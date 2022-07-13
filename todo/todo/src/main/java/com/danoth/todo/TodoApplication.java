package com.danoth.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing // jpa auditing 애노테이션들이 활성화 할 수 있도록 해줌
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

}
