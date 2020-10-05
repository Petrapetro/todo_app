package com.gfa.task2.controllers;

import com.gfa.task2.models.Assignee;
import com.gfa.task2.models.Todo;
import com.gfa.task2.services.AssigneeService;
import com.gfa.task2.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/todo")
public class EditTodoController {
    final
    TodoService todoService;

    public EditTodoController(TodoService todoService, AssigneeService assigneeService) {
        this.todoService = todoService;
        this.assigneeService = assigneeService;
    }

    final
    AssigneeService assigneeService;

    @RequestMapping(value="/{id}/edit", method = RequestMethod.GET)
    public String editTodoGet(@PathVariable("id") long id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        model.addAttribute("assignees", assigneeService.getAssignees());
        return "edit";
    }

    @RequestMapping(value="/{id}/edit", method = RequestMethod.POST)
    public String editTodoPost(@PathVariable("id") long id,
                               @RequestParam(name="title", required = false) String title,
                               @RequestParam(name="isUrgent", required = false) String isUrgent,
                               @RequestParam(name="isDone", required = false) String isDone,
                               @RequestParam(name="assignee", required = false) String assigneeName ) {
        if (isUrgent == null) {
            isUrgent = "false";
        }
        if (isDone == null) {
            isDone = "false";
        }
        Assignee assignee = assigneeService.getAssignee(assigneeName);
        List<Todo> todos = assignee.getTodos();
        if (!todos.contains(todoService.getTodo(id))) {
            todos.add(todoService.getTodo(id));
            assignee.setTodos(todos);
        }
        todoService.editTodo(id, title, Boolean.parseBoolean(isUrgent), Boolean.parseBoolean(isDone), assignee);
        return "redirect:/todo/";
    }
}
