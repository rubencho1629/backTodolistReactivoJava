package com.ruben.backend_to_do_list.infrastructure;

import com.ruben.backend_to_do_list.application.TaskService;
import com.ruben.backend_to_do_list.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
}
