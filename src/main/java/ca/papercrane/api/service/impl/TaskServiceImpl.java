package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.TaskRepository;
import ca.papercrane.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task getByTaskId(Integer taskId) {
        return taskRepository.findByTaskId(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found with taskId: " + taskId));
    }

    @Override
    public List<Task> getAllByProjectId(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId).orElseThrow(() -> new ResourceNotFoundException("No tasks found for projectId: " + projectId));
    }

    @Override
    public Integer create(Integer projectId, String description, Date startDate, Date deadline, Double expectedWorkHours) {
        final Task createdTask = taskRepository.save(new Task(projectId, description, startDate, deadline, expectedWorkHours));
        return createdTask.getTaskId();
    }

    @Override
    public Integer create(Task task) {
        final Task createdTask = taskRepository.save(task);
        return createdTask.getTaskId();
    }

    @Override
    public void update(Task task) {
        final Task existingTask = getByTaskId(task.getTaskId());
        existingTask.setDescription(task.getDescription());
        existingTask.setStartDate(task.getStartDate());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setExpectedWorkHours(task.getExpectedWorkHours());
        save(existingTask);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void deleteByTaskId(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Long totalCount() {
        return taskRepository.count();
    }

}