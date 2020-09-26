package com.gfa.task2.controllers;

import com.gfa.task2.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/todo")
public class AssigneeController {

    final
    AssigneeService assigneeService;

    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }

    @RequestMapping(value="/assignees/", method = RequestMethod.GET)
    public String listAssignees(Model model) {
        model.addAttribute("assignees", assigneeService.getAssignees());
        return "assignees";
    }
}
