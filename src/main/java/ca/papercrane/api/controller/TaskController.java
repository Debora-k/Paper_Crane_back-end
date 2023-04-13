package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    /**
     * Gets a list of every task that belongs to a specific project id.
     *
     * @param projectId The id of the project that the list of tasks is for.
     * @return The list.
     */
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> taskByProjectId(@PathVariable Integer projectId) {
        try {
            val projectTaskList = taskService.getAllByProjectId(projectId);
            return new ResponseEntity<>(projectTaskList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets a task by it's id value.
     *
     * @param taskId The task id to search for.
     * @return The found task data.
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> taskByTaskId(@PathVariable Integer taskId) {
        try {
            val task = taskService.getByTaskId(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}