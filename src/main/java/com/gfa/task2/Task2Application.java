package com.gfa.task2;

import com.gfa.task2.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task2Application implements CommandLineRunner {
    final
    TodoService todoService;

    public Task2Application(TodoService todoService) {
        this.todoService = todoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Task2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        todoService.addTodo("feed the cats", true, false);
        todoService.addTodo("wash dishes", false, true);
        todoService.addTodo("make the beds", true, false);
        todoService.addTodo("do homework", false, true);
        System.out.println(todoService.getTodos().stream().toString());
    }
}
