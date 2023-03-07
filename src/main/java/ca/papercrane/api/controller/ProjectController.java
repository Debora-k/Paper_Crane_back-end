package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.service.impl.ProjectServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/")
public final class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @PostConstruct
    public void init() {
        createFakeProject();
        System.out.println("Fake project created view at: http://localhost:8080/api/projects/id/1");
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Integer id) {
        try {
            Project project = projectService.findById(id);
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