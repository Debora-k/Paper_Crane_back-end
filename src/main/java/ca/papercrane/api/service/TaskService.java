package ca.papercrane.api.service;

import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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

    /**
     * Crates a new TaskService.
     *
     * @param taskRepository The task repository.
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a new Task.
     *
     * @param projectId         The projectId the task will be associated with.
     * @param description       The description of the task.
     * @param startDate         The date the task was created.
     * @param deadline          The date the task must be completed by.
     * @param expectedWorkHours The amount of work hours this task should take to complete.
     * @return The newly created task object.
     */
    public Task createTask(Integer projectId, String description, Date startDate, Date deadline, Double expectedWorkHours) {
        final Task newTask = new Task(projectId, description, startDate, deadline, expectedWorkHours);
        //TODO: Checks/Validation before saving.
        return saveTask(newTask);
    }

    /**
     * Updates a Task within the database.
     *
     * @param task The task object with the updated task data.
     * @return The updated and saved task.
     */
    public void updateTask(Task task) {
        final Optional<Task> existingTaskOptional = taskRepository.findByTaskId(task.getTaskId());
        if (existingTaskOptional.isPresent()) {
            final Task existingTask = existingTaskOptional.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setStartDate(task.getStartDate());
            existingTask.setDeadline(task.getDeadline());
            existingTask.setExpectedWorkHours(task.getExpectedWorkHours());
            saveTask(task);
        } else {
            //TODO: Exception.
        }
    }

    /**
     * Returns an optional of the task that exists with the specified taskId.
     *
     * @param taskId The integer taskId being searched for.
     * @return The found task.
     */
    public Optional<Task> getTaskById(Integer taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    /**
     * Returns a list of tasks that belong to a specified projectId.
     *
     * @param projectId The projectId that the list of tasks belong to.
     * @return The list of tasks.
     */
    public List<Task> getAllByProjectId(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    /**
     * Saves a task into the database.
     *
     * @param task The task being saved into the database.
     * @return the saved task.
     */
    public Task saveTask(Task task) {
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