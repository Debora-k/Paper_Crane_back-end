package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.service.impl.ProjectServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @PostConstruct
    public void init() {
        createFakeProject();
        System.out.println("Fake project created view at: http://localhost:8080/api/v1/projects/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Project>> getAll() {
        try {
            val projectList = projectService.getAll();
            return new ResponseEntity<>(projectList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Integer projectId) {
        try {
            val project = projectService.getByProjectId(projectId);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeProject() {
        Project project = new Project(1, 1, "Test Project");
        projectService.save(project);
    }


}