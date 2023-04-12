package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.training.VideoTraining;
import ca.papercrane.api.repository.VideoTrainingRepository;
import ca.papercrane.api.service.VideoTrainingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoTrainingServiceImpl implements VideoTrainingService {

    private final VideoTrainingRepository trainingRepository;

    @Override
    public List<VideoTraining> getAll() {
        val trainingList = trainingRepository.findAll();
        if (trainingList.isEmpty()) {
            throw new ResourceNotFoundException("No training found.");
        }
        return trainingList;
    }

    @Override
    public VideoTraining getByVideoId(Integer videoId) {
        return trainingRepository.findByVideoId(videoId).orElseThrow(() -> new ResourceNotFoundException("Training not found for id: " + videoId));
    }

    @Override
    public Integer create(Integer projectId, String videoLink, String description) {
        val newTraining = new VideoTraining(projectId, videoLink, description);
        val savedTraining = trainingRepository.save(newTraining);
        return savedTraining.getVideoId();
    }

    @Override
    public void update(Integer videoId, String description, String videoLink) {
        val existingTraining = getByVideoId(videoId);
        existingTraining.setDescription(description);
        existingTraining.setVideoLink(videoLink);
        save(existingTraining);
    }

    @Override
    public void save(VideoTraining training) {
        trainingRepository.save(training);
    }

    @Override
    public void saveByVideoId(Integer videoId) {
        val videoTraining = getByVideoId(videoId);
        save(videoTraining);
    }

    @Override
    public void deleteByVideoId(Integer videoId) {
        val videoTraining = getByVideoId(videoId);
        trainingRepository.delete(videoTraining);
    }

    @Override
    public Long totalCount() {
        return trainingRepository.count();
    }

}