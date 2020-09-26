package com.gfa.task2.controllers;

import com.gfa.task2.models.Todo;
import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/todo")
public class EditTodoController {
    final
    TodoService todoService;

    public EditTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value="/{id}/edit", method = RequestMethod.GET)
    public String editTodoGet(@PathVariable("id") long id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @RequestMapping(value="/{id}/edit", method = RequestMethod.POST)
    public String editTodoPost(@PathVariable("id") long id,
                               @RequestParam(name="title", required = false) String title,
                               @RequestParam(name="isUrgent", required = false) String isUrgent,
                               @RequestParam(name="isDone", required = false) String isDone) {
        if (isUrgent == null) {
            isUrgent = "false";
        }
        if (isDone == null) {
            isDone = "false";
        }
        todoService.editTodo(id, title, Boolean.valueOf(isUrgent), Boolean.valueOf(isDone));
        return "redirect:/todo/";
    }
}
