package com.danoth.todo.todoController;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // final이 붙은 생성자를 자동으로 생성
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/")
    public String root() {
        return "redirect:todolist";
    }

    /**
     * 조회
     * @param model
     * @return
     */
    @RequestMapping("/todolist")
    public String list(Model model) {
        model.addAttribute("todo",todoService.getList());
        return "todolist";
    }

    /**
     * 추가
     */
    @PostMapping("/todolist/create")
    public String todoCreate(TodoDto todoDto) {
        todoService.create(TodoDto.toEntity(todoDto)); // entity
        return "redirect:/todolist"; // 주소경로에 공백도 인식하기 때문에 공백있으면 404 에러 뜬다.
    }

    /**
     * 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/todolist/delete/{id}")
    public String todoDelete(@PathVariable Long id) { // @PathVariable은 ajax data안에 안들어감
        todoService.delete(id);
        return "redirect:/todolist";
    }


    /*
    * 수정
    * */
    @PutMapping("/todolist/update/{id}")
    public String todoUpdate(@PathVariable Long id, @RequestParam String content) {
        todoService.update(id, content);
        return "redirect:/todolist";
    }


    /*
    * url상에서 데이터를 전달하는 경우 @RequestParam을,
    * 그 외의 경우 @RequestBody  contentType:'application/json; charset=utf-8' 넣어줘야 한다
     */
}
