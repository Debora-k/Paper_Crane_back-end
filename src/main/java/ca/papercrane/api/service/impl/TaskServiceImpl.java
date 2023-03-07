package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.TaskRepository;
import ca.papercrane.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findById(Integer taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found!"));
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Long totalCount() {
        return taskRepository.count();
    }

}