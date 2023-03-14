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
    public VideoTraining getByVideoId(Integer videoId) {
        return trainingRepository.findByVideoId(videoId).orElseThrow(() -> new ResourceNotFoundException("Training not found for id: " + videoId));
    }

    @Override
    public Integer create(Integer projectId, String videoLink, String description) {
        final VideoTraining createdTraining = trainingRepository.save(new VideoTraining(projectId, videoLink, description));
        return createdTraining.getVideoId();
    }

    @Override
    public Integer create(VideoTraining training) {
        final VideoTraining createdTraining = trainingRepository.save(training);
        return createdTraining.getVideoId();
    }

    @Override
    public void update(VideoTraining training) {
        final VideoTraining existingTraining = getByVideoId(training.getVideoId());
        existingTraining.setDescription(training.getDescription());
        existingTraining.setVideoLink(training.getVideoLink());
        save(existingTraining);
    }

    @Override
    public void save(VideoTraining training) {
        trainingRepository.save(training);
    }

    @Override
    public void delete(VideoTraining training) {
        trainingRepository.delete(training);
    }

    @Override
    public void deleteByVideoId(Integer videoId) {
        final VideoTraining training = getByVideoId(videoId);
        trainingRepository.delete(training);
    }

    @Override
    public Long totalCount() {
        return trainingRepository.count();
    }

}