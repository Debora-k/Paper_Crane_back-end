package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.service.impl.TaskServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostConstruct
    public void init() {
        createFakeTask();
        System.out.println("Fake task created view at: http://localhost:8080/api/v1/projects/tasks/1");
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> taskByProjectId(@PathVariable Integer projectId) {
        try {
            val projectTaskList = taskService.getAllByProjectId(projectId);
            return new ResponseEntity<>(projectTaskList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> taskByTaskId(@PathVariable Integer taskId) {
        try {
            val task = taskService.getByTaskId(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Just to test for now.
     */
    public void createFakeTask() {
        val startDate = LocalDate.of(2020, Calendar.NOVEMBER, 14);
        val endDate = LocalDate.of(2020, Calendar.NOVEMBER, 5);
        val task = new Task(1, "Test task", startDate, endDate, 40.1);
        taskService.save(task);
    }

}