package ca.papercrane.api.service;

import ca.papercrane.api.project.task.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    Task getByTaskId(Integer taskId);

    List<Task> getAllByProjectId(Integer projectId);

    Integer create(Integer projectId, String description, LocalDate startDate, LocalDate deadline, Double expectedWorkHours);

    Integer create(Task task);

    void update(Task task);

    void save(Task task);

    void delete(Task task);

    void deleteByTaskId(Integer taskId);

    Long totalCount();

}