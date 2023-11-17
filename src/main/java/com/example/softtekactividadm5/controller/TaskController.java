package com.example.softtekactividadm5.controller;

import com.example.softtekactividadm5.service.TaskService;
import com.example.softtekactividadm5.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la gestión de tareas en la interfaz web.
 */
@Controller
@RequestMapping
public class TaskController {
    @Autowired
    private TaskService service;

    /**
     * Obtiene la lista de tareas y las agrega al modelo para mostrarlas en la vista principal.
     *
     * @param model El modelo utilizado para la vista.
     * @return La vista principal que muestra la lista de tareas.
     */
    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = service.getTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    /**
     * Muestra el formulario para agregar una nueva tarea.
     *
     * @param model El modelo utilizado para la vista.
     * @return La vista del formulario para agregar una nueva tarea.
     */
    @GetMapping("/tasks/new")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    /**
     * Guarda una nueva tarea y redirige a la vista principal.
     *
     * @param t     La tarea a guardar.
     * @param model El modelo utilizado para la vista.
     * @return Redirección a la vista principal.
     */
    @PostMapping("/save")
    public String save(@Validated Task t, Model model) {
        service.saveTask(t);
        return "redirect:/tasks";
    }

    /**
     * Maneja la página de edición de una tarea existente.
     *
     * @param id    El ID de la tarea a editar.
     * @param model El modelo utilizado para la vista.
     * @return La vista del formulario de edición de tarea.
     */
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable int id, Model model) {
        Optional<Task> task = service.getTaskById(id);
        model.addAttribute("task", task.orElse(new Task()));
        return ("editForm");
    }

    /**
     * Actualiza una tarea existente y redirige a la vista principal.
     *
     * @param t     La tarea actualizada.
     * @param model El modelo utilizado para la vista.
     * @return Redirección a la vista principal.
     */
    @PostMapping("/update")
    public String updateTask(@Validated Task t, Model model) {
        Optional<Task> existingTask = service.getTaskById(t.getId());

        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();
            updatedTask.setName(t.getName());
            updatedTask.setDescription(t.getDescription());
            updatedTask.setCompleted(t.isCompleted());
            service.saveTask(updatedTask); // guarda la tarea actualizada
        }
        return "redirect:/tasks";
    }

    /**
     * Elimina una tarea por su ID y redirige a la vista principal.
     *
     * @param id El ID de la tarea a eliminar.
     * @return Redirección a la vista principal.
     */
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
