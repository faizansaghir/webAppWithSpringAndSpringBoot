package io.github.faizansaghir.webAppWithSpringAndSpringBoot.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos;
    private static int todoCount;
    static {
        todos = new ArrayList<>();
        todoCount = 0;
        todos.add(new Todo(++todoCount, "faizan","Learn Spring", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++todoCount, "faizan","Learn SpringBoot", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++todoCount, "faizan","Learn Spark Scala", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> getTodosForUsername(String username){
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo newTodo = new Todo(++todoCount, username, description, targetDate, done);
        todos.add(newTodo);
    }
}
