package com.gfa.task2.repositories;

import com.gfa.task2.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
        Todo findById(long id);
        List<Todo> findByTitleContaining(String searchWord);
        List<Todo> findAll();

}
