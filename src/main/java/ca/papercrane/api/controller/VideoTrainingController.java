package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.training.VideoTraining;
import ca.papercrane.api.service.impl.VideoTrainingServiceImpl;
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
@RequestMapping("api/v1/training")
public class VideoTrainingController {

    private final VideoTrainingServiceImpl trainingService;

    @PostConstruct
    public void init() {
        createFakeVideoTraining();
        System.out.println("Fake VideoTraining created view at: http://localhost:8080/api/v1/training/1");
    }

    @GetMapping("")
    public ResponseEntity<List<VideoTraining>> getAll() {
        try {
            val trainingList = trainingService.getAll();
            return new ResponseEntity<>(trainingList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoTraining> getTraining(@PathVariable Integer id) {
        try {
            val videoTraining = trainingService.getByVideoId(id);
            return new ResponseEntity<>(videoTraining, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeVideoTraining() {
        trainingService.create(1, "link", "text");
        trainingService.create(2, "link", "text");
        trainingService.create(3, "link", "text");
        trainingService.create(1, "link", "text");
        trainingService.create(1, "link", "text");
    }

}
