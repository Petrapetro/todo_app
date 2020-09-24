package com.gfa.task2.controllers;


import com.gfa.task2.models.Todo;
import com.gfa.task2.repositories.TodoRepository;
import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/todo")
public class TodoController {

    final
    TodoService todoService;

    public TodoController(TodoService todoService) {
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

    @GetMapping(path = "/?isActive")
    public String listActiveTodos(Model model, @RequestParam(name = "isActive") boolean isActive) {
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

    @GetMapping(path = "/add")
    public String getForm() {
        return "form";
    }

    @PostMapping(path = "/add")
    public String postForm(@RequestParam(name = "todo") String todo,
                           @RequestParam(name = "relevance") boolean relevance) {
        todoService.addTodo(todo, relevance, false);
        return "redirect:/todo/";
    }

    @PostMapping(path="{todo.id}/delete")
    public String deleteTodo(@PathVariable(name="todo.id") long id) {
        todoService.deleteTodo(id);
        return "redirect:/todo/";
    }

}
