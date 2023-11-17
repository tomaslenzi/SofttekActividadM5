package com.example.softtekactividadm5.service.impl;

import com.example.softtekactividadm5.service.TaskService;
import com.example.softtekactividadm5.repository.TaskRepository;
import com.example.softtekactividadm5.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceJPA implements TaskService {
    @Autowired
    private TaskRepository data;

    @Override
    public List<Task> getTasks() {
        return (List<Task>) data.findAll();
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return data.findById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return data.save(task);
    }

    @Override
    public void deleteTask(int id) {
        data.deleteById(id);

    }
}
