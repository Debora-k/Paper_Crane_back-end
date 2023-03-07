package ca.papercrane.api.service;

import ca.papercrane.api.project.task.Task;

public interface TaskService {

    Task findById(Integer taskId);

    void save(Task task);

    Long totalCount();

}