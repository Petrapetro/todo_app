package com.gfa.task2.controllers;

import com.gfa.task2.models.Todo;
import com.gfa.task2.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/todo")
public class SearchTodoController {
    final
    TodoService todoService;

    public SearchTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

/*    @RequestMapping(value="/search/", method = RequestMethod.GET)
    public String searchTodoGet(@RequestParam(value="searchWord", required = false) String searchWord, Model model) {
        model.addAttribute("SearchWord", searchWord);
        return "redirect:/todo/search/?searchWord=" + searchWord;
    }*/

    @RequestMapping(value="/search/", method = RequestMethod.POST)
    public String searchTodoPost(@RequestParam(value="searchWord", required = false) String searchWord, Model model) {
        List<Todo> todos = todoService.searchTodo(searchWord);
        model.addAttribute("todos", todos);
        model.addAttribute("SearchWord", searchWord);
        return "search-result";
    }
}
