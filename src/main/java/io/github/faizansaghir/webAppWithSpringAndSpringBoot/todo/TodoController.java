package io.github.faizansaghir.webAppWithSpringAndSpringBoot.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listTodos(ModelMap model){
        List<Todo> todos = todoService.getTodosForUsername("faizan");
        model.addAttribute("todos", todos);
        return "listTodos";
    }
}
