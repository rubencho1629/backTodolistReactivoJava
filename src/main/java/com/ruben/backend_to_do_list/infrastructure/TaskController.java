package com.ruben.backend_to_do_list.infrastructure;

import com.ruben.backend_to_do_list.application.TaskService;
import com.ruben.backend_to_do_list.domain.Task;
import com.ruben.backend_to_do_list.domain.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public Flux<Task> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Task> save(@RequestBody Task task) {
        return service.save(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    @GetMapping("/category/{category}")
    public Flux<Task> getByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/priority/{priority}")
    public Flux<Task> getByPriority(@PathVariable String priority) {
        return service.findByPriority(priority);
    }

    @PutMapping("/{id}/status")
    public Mono<Task> updateStatus(@PathVariable String id, @RequestParam TaskStatus status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/status/{status}")
    public Flux<Task> getByStatus(@PathVariable String status) {
        try {
            TaskStatus parsedStatus = TaskStatus.valueOf(status.toUpperCase());
            return service.findByStatus(parsedStatus);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estado inválido: " + status + ". Debe ser PENDING o COMPLETED"
            );
        }
    }

}
