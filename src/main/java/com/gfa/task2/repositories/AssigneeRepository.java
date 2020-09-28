package com.gfa.task2.repositories;

import com.gfa.task2.models.Assignee;
import com.gfa.task2.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssigneeRepository extends CrudRepository<Assignee, Long> {
    List<Assignee> findAll();
    Assignee findById(long id);
    Assignee findByName(String name);
}
