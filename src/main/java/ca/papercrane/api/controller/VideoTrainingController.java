package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.training.VideoTraining;
import ca.papercrane.api.service.impl.VideoTrainingServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/training/")
public class VideoTrainingController {

    @Autowired
    private VideoTrainingServiceImpl trainingService;

    @PostConstruct
    public void init() {
        createFakeVideoTraining();
        System.out.println("Fake VideoTraining created view at: http://localhost:8080/api/training/1");
    }

    @GetMapping("{id}")
    public ResponseEntity<VideoTraining> getTraining(@PathVariable Integer id) {
        try {
            final VideoTraining videoTraining = trainingService.getByVideoId(id);
            return new ResponseEntity<>(videoTraining, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeVideoTraining() {
        final VideoTraining training = new VideoTraining(1, "link", "Test");
        trainingService.save(training);
    }

}
