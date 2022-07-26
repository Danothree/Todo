package com.danoth.todo.todoController;

import com.danoth.todo.dto.TodoDto;
import com.danoth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor // final이 붙은 생성자를 자동으로 생성
public class TodoController {

    private final TodoService todoService;

/*    @RequestMapping("/")
    public String root() {
        return "redirect:todolist";
    }*/

    /**
     * 조회
     * @param model
     * @return
     */
    @RequestMapping("/todolist")
    public String list(Model model) { // controller에서 생선한 데이터를 담아서 view에 전달할 때 사용하는 객체(키,밸류)
        model.addAttribute("todo",todoService.getList());
        return "todolist";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "calendar";
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
    public String todoUpdateContent(@PathVariable Long id, @RequestParam String content) {
        todoService.updateContent(id, content);
        return "redirect:/todolist";
    }



    @PutMapping("/todolist/check/{id}")
    @ResponseBody
    public Map<String, Object> todoUpdateCheck(@PathVariable Long id, @RequestParam("isCompleted") boolean isCompleted) {
        Map<String, Object> result = new HashMap<>();
        result.put("result","success");
        todoService.updateCheck(id, isCompleted);

        return result;
    }




    /*
    * url상에서 데이터를 전달하는 경우 @RequestParam을,
    * 그 외의 경우 @RequestBody  contentType:'application/json; charset=utf-8' 넣어줘야 한다
     */
}
