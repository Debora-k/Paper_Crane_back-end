package ca.papercrane.api.service;

import ca.papercrane.api.project.training.VideoTraining;

public interface VideoTrainingService {

    VideoTraining findById(Integer videoId);

    void save(VideoTraining training);

    Long totalCount();

}