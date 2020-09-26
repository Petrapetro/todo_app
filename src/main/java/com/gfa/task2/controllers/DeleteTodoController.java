package com.gfa.task2.controllers;

import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/todo")
public class DeleteTodoController {

    final
    TodoService todoService;

    public DeleteTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value="/{id}/delete", method = RequestMethod.POST)
    public String deleteTodo(@PathVariable("id") long id) {
        todoService.deleteTodo(id);
        return "redirect:/todo/";
    }
}
