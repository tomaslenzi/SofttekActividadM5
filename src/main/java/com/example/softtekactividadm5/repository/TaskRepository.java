package com.example.softtekactividadm5.repository;

import com.example.softtekactividadm5.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
