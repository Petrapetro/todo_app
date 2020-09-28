package com.gfa.task2.controllers;

import com.gfa.task2.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/todo/assignees")
public class AssigneeController {

    final
    AssigneeService assigneeService;

    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String listAssignees(Model model) {
        model.addAttribute("assignees", assigneeService.getAssignees());
        return "assignees";
    }

    @GetMapping(path = "/add")
    public String getAssigneeForm() {
        return "assignee-form";
    }

    @PostMapping(path = "/add")
    public String postAssigneeForm(@RequestParam(name = "assignee") String assignee,
                           @RequestParam(name = "email") String email) {
        assigneeService.addAssignee(assignee, email);
        return "redirect:/todo/assignees/";
    }


}
