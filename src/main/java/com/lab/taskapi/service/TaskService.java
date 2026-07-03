package com.lab.taskapi.service;

import com.lab.taskapi.model.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public Collection<Task> findAll() {
        return tasks.values();
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task create(Task task) {
        long id = idSequence.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public Optional<Task> update(Long id, Task updated) {
        if (!tasks.containsKey(id)) {
            return Optional.empty();
        }
        updated.setId(id);
        tasks.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        return tasks.remove(id) != null;
    }
}
