package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.training.VideoTraining;
import ca.papercrane.api.repository.VideoTrainingRepository;
import ca.papercrane.api.service.VideoTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class VideoTrainingServiceImpl implements VideoTrainingService {

    @Autowired
    private VideoTrainingRepository trainingRepository;

    @Override
    public VideoTraining findById(Integer videoId) {
        return trainingRepository.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("Training not found!"));
    }

    @Override
    public void save(VideoTraining training) {
        trainingRepository.save(training);
    }

    @Override
    public Long totalCount() {
        return trainingRepository.count();
    }

}