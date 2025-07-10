package com.ruben.backend_to_do_list.infrastructure;


import com.ruben.backend_to_do_list.domain.Task;
import com.ruben.backend_to_do_list.domain.TaskStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
    Flux<Task> findByCompletedFalse();
    Flux<Task> findByCompletedTrue();
    Flux<Task> findByCategoryIgnoreCase(String category);
    Flux<Task> findByPriority(String priority);
    Flux<Task> findByStatus(TaskStatus status);
    Flux<Task> findByCategory(String category);

}
