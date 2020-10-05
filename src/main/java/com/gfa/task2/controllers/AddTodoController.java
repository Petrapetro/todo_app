package com.gfa.task2.controllers;

import com.gfa.task2.services.AssigneeService;
import com.gfa.task2.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/todo")
public class AddTodoController {

    final
    TodoService todoService;

    final
    AssigneeService assigneeService;

    public AddTodoController(TodoService todoService, AssigneeService assigneeService) {
        this.todoService = todoService;
        this.assigneeService = assigneeService;
    }

    @GetMapping(path = "/add")
    public String getForm(Model model) {
        model.addAttribute("assignees", assigneeService.getAssignees());
        return "form";
    }

    @PostMapping(path = "/add")
    public String postForm(@RequestParam(name = "todo") String todo,
                           @RequestParam(name = "relevance") boolean relevance,
                           @RequestParam(name = "assignee") String assigneeName) {
        todoService.addTodo(todo, relevance, false, assigneeService.getAssignee(assigneeName));
        return "redirect:/todo/";
    }

}
