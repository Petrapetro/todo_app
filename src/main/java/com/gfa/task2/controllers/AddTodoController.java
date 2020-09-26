package com.gfa.task2.controllers;

import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/todo")
public class AddTodoController {

    final
    TodoService todoService;

    public AddTodoController(TodoService todoService) {
        this.todoService = todoService;
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

}
