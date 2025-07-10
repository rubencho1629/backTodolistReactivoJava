package com.ruben.backend_to_do_list.application;

import com.ruben.backend_to_do_list.domain.Task;
import com.ruben.backend_to_do_list.infrastructure.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
