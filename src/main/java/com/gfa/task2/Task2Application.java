package com.gfa.task2;

import com.gfa.task2.services.AssigneeService;
import com.gfa.task2.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task2Application implements CommandLineRunner {
    final
    TodoService todoService;

    public Task2Application(TodoService todoService, AssigneeService assigneeService) {
        this.todoService = todoService;
        this.assigneeService = assigneeService;
    }

    final
    AssigneeService assigneeService;


    public static void main(String[] args) {
        SpringApplication.run(Task2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(todoService.getTodos().stream().toString());
    }
}
