package io.github.faizansaghir.webAppWithSpringAndSpringBoot.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos;

    static {
        todos = new ArrayList<>();
        todos.add(new Todo(1, "faizan","Learn Spring", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(2, "faizan","Learn SpringBoot", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(3, "faizan","Learn Spark Scala", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> getTodosForUsername(String username){
        return todos;
    }
}
