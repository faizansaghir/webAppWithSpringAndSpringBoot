package io.github.faizansaghir.webAppWithSpringAndSpringBoot.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model){
        Todo todo = new Todo(0, (String)model.get("username"), "", LocalDate.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodoPost(ModelMap model, Todo todo){
        String username = (String)model.get("username");
        todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }
}
