package com.gfa.task2.services;

import com.gfa.task2.models.Todo;
import com.gfa.task2.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    final
    TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

    public Todo getTodo(long id) {
        return todoRepository.findById(id);
    }

    public void addTodo(String title, boolean urgent, boolean isDone) {
        todoRepository.save(new Todo(title, urgent, isDone));
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    public void editTodo(long id, String title, boolean isUrgent, boolean isDone) {
        Todo todo = todoRepository.findById(id);
        todo.setTitle(title);
        todo.setIsDone(isDone);
        todo.setIsUrgent(isUrgent);
        todoRepository.save(todo);
    }

    public List<Todo> searchTodo(String searchWord) {
        return todoRepository.findByTitleContaining(searchWord);
    }
}
