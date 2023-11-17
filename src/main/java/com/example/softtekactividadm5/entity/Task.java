package com.example.softtekactividadm5.entity;

import jakarta.persistence.*;
/**
 * Representa una tarea en el sistema.
 */
@Entity
@Table(name ="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;
    private String description;
    private boolean completed;

    // Constructor sin argumentos necesario para JPA
    public Task(){};

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
