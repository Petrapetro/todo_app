package com.gfa.task2.services;

import com.gfa.task2.models.Assignee;
import com.gfa.task2.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneeService {

    final
    AssigneeRepository assigneeRepository;

    public AssigneeService(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    public List<Assignee> getAssignees() {
        return assigneeRepository.findAll();
    }

    public void addAssignee(String name, String email) {
        Assignee assignee = new Assignee(null, name, email, null);
        assigneeRepository.save(assignee);
    }

    public void deleteAssignee(long id) {
        assigneeRepository.deleteById(id);
    }
}
