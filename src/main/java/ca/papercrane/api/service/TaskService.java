package ca.papercrane.api.service;

import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving data from the {@link TaskRepository}
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

    /**
     * Persists a Task into the database.
     *
     * @param task The task being inserted into the database.
     * @return the Task object.
     */
    public Task persistTask(Task task) {

        //TODO: Check for the following before saving a task to the database.

        //TODO: projectId must not be null, a project with the projectId must exist.

        //TODO: description must not be null, description must follow required length or other requirements.

        //TODO: start and deadline dates must not be null, deadline must be set for a time after the start date not before.

        //TODO: expected work hours and progress work hours must not be null, by default 0 and must be positive values.

        return taskRepository.save(task);
    }

    /**
     * Deletes a task from the database.
     *
     * @param task the task being deleted.
     */
    public void delete(Task task) {
        //TODO: Checks or validation before deleting.
        taskRepository.delete(task);
    }

    /**
     * Deletes a task by the specified taskId.
     *
     * @param taskId the id of the task being deleted.
     */
    public void deleteById(Integer taskId) {
        //TODO: Checks or validation before deleting.
        taskRepository.deleteById(taskId);
    }

    /**
     * Returns the total amount of tasks stored within the repository.
     *
     * @return the count.
     */
    public Long totalTaskCount() {
        return taskRepository.count();
    }

}