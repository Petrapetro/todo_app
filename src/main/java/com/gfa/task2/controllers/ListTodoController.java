package com.gfa.task2.controllers;
import com.gfa.task2.models.Todo;
import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/todo")
public class ListTodoController {

    final
    TodoService todoService;

    public ListTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = {"/", "/list"})
    public String list(Model model) {
        model.addAttribute("todos", todoService.getTodos());
        return "todolist";
    }

    @GetMapping(path = "/activeTodos")
    public String listActualTodos(Model model) {
        List<Todo> todoList = todoService.getTodos();
        List<Todo> todoListSelected = todoList.stream()
                .filter(x -> x.isIsDone() == false)
                .collect(Collectors.toList());
        model.addAttribute("todos", todoListSelected);
        return "todolist";
    }

    @GetMapping(path = "/completedTodos")
    public String listCompletedTodos(Model model) {
        List<Todo> todoList = todoService.getTodos();
        List<Todo> todoListSelected = todoList.stream()
                .filter(x -> x.isIsDone() == true)
                .collect(Collectors.toList());
        model.addAttribute("todos", todoListSelected);
        return "todolist";
    }

    @GetMapping(path = "/urgentTodos")
    public String listUrgentTodos(Model model) {
        List<Todo> todoList = todoService.getTodos();
        List<Todo> todoListSelected = todoList.stream()
                .filter(x -> x.isIsUrgent() == true)
                .collect(Collectors.toList());
        model.addAttribute("todos", todoListSelected);
        return "todolist";
    }

    @GetMapping(path = "/?isActive")
    public String listActiveTodos(Model model, @RequestParam(name = "isActive", required = false) boolean isActive) {
        List<Todo> todoList = todoService.getTodos();
        List<Todo> todoListSelected;
        if (isActive) {
            todoListSelected = todoList.stream()
                    .filter(x -> !x.isIsDone())
                    .collect(Collectors.toList());
        } else {
            todoListSelected = todoList.stream()
                    .filter(Todo::isIsDone)
                    .collect(Collectors.toList());
        }
        model.addAttribute("todos", todoListSelected);
        return "todolist";
    }
}
