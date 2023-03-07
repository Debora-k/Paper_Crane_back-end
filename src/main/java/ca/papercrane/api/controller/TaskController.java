package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.service.impl.TaskServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/tasks/")
public final class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostConstruct
    public void init() {
        createFakeTask();
        System.out.println("Fake task created view at: http://localhost:8080/api/tasks/id/1");
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        try {
            Task task = taskService.findById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeTask() {
        final Date startDate = new Date(2020, 10, 14);
        final Date endDate = new Date(2020, 11, 5);
        final Task task = new Task(1, "Test task", startDate, endDate, 40.1);
        taskService.save(task);
    }

}