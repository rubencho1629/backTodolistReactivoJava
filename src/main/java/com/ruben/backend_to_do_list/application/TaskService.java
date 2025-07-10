package com.ruben.backend_to_do_list.application;

import com.ruben.backend_to_do_list.domain.Task;
import com.ruben.backend_to_do_list.domain.TaskStatus;
import com.ruben.backend_to_do_list.infrastructure.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
;


@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Flux<Task> findAll() {
        return repository.findAll();
    }

    public Mono<Task> save(Task task) {
        return repository.save(task);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
    public Flux<Task> findPending() {
        return repository.findByCompletedFalse();
    }

    public Flux<Task> findCompleted() {
        return repository.findByCompletedTrue();
    }

    public Flux<Task> findByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    public Flux<Task> findByPriority(String priority) {
        return repository.findByPriority(priority.toUpperCase());
    }
    public Flux<Task> findByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }
    public Mono<Task> updateStatus(String id, TaskStatus status) {
        return repository.findById(id)
                .flatMap(task -> {
                    task.setStatus(status);
                    return repository.save(task);
                });
    }
    public Mono<Task> updateTask(String id, Task updatedTask) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada con id: " + id)))
                .flatMap(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCategory(updatedTask.getCategory());
                    existingTask.setPriority(updatedTask.getPriority());
                    existingTask.setDueDate(updatedTask.getDueDate());
                    existingTask.setStatus(updatedTask.getStatus());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    return repository.save(existingTask);
                });
    }

}
