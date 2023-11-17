package com.example.softtekactividadm5.controller;

import com.example.softtekactividadm5.entity.Task;
import com.example.softtekactividadm5.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de tareas mediante una API.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
    @Autowired
    private ITaskService taskService;

    /**
     * Obtiene todas las tareas.
     *
     * @return Lista de tareas.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Obtiene una tarea por su ID.
     *
     * @param id ID de la tarea.
     * @return ResponseEntity con la tarea si se encuentra, o ResponseEntity.notFound() si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Guarda una nueva tarea.
     *
     * @param task La tarea a guardar.
     * @return ResponseEntity con la tarea guardada.
     */
    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    /**
     * Actualiza una tarea existente por su ID.
     *
     * @param id   ID de la tarea a actualizar.
     * @param task La tarea actualizada.
     * @return ResponseEntity con la tarea actualizada si se encuentra, o ResponseEntity.notFound() si no.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> existingTask = taskService.getTaskById(id);

        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();
            updatedTask.setName(task.getName());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setCompleted(task.isCompleted());
            taskService.saveTask(updatedTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id ID de la tarea a eliminar.
     * @return ResponseEntity.noContent() si se elimina correctamente, o ResponseEntity.notFound() si no.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
