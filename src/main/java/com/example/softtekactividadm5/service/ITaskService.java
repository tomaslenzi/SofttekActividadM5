package com.example.softtekactividadm5.service;

import com.example.softtekactividadm5.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    List<Task> getTasks();

    Optional<Task> getTaskById(int id);

    Task saveTask(Task task);

    void deleteTask(int id);
}
