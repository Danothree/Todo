package com.danoth.todo.testController;

import com.danoth.todo.entitiy.Todolist;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TodoService todoService;

    @GetMapping("/todolist")
    public String list() {
        return "todolist";
    }

    @RequestMapping("/todolist")
    public String list(Model model) {
        List<Todolist> todolist = this.todoService.getList();
        model.addAttribute("todolist",todolist);
        return "todolist";
    }

    @PostMapping("/todolist/create")
    public String todoCreate(@RequestParam String todolistContent) {
        todoService.create(todolistContent);
        return "redirect:/todolist"; // 주소경로에 공백도 인식하기 때문에 공백있으면 404 에러 뜬다.
    }

}
