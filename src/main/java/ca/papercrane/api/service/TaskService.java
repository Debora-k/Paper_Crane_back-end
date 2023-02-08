package ca.papercrane.api.service;

import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving data from the {@link TaskRepository}
 */
@Service
public record TaskService(TaskRepository taskRepository) {

    @Autowired
    public TaskService {
    }

    /**
     * Returns an Optional containing the Task that exists with the specified taskId.
     *
     * @param taskId The integer taskId being searched for.
     * @return An Optional containing the found Task, or an empty Optional if no Task is found with the specified taskId.
     */
    public Optional<Task> getTaskById(Integer taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    /**
     * Returns a List of Task objects that belong to the specified projectId.
     *
     * @param projectId The projectId that the list of tasks belong to.
     * @return The List of Task objects.
     */
    public List<Task> getAllByProjectId(Integer projectId) {
        return taskRepository.findByProjectId(projectId);
    }

}