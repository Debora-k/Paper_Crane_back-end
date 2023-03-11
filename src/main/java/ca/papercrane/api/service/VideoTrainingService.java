package ca.papercrane.api.service;

import ca.papercrane.api.project.training.VideoTraining;

public interface VideoTrainingService {

    VideoTraining getByVideoId(Integer videoId);

    Integer create(Integer projectId, String videoLink, String description);

    Integer create(VideoTraining training);

    void update(VideoTraining training);

    void save(VideoTraining training);

    void delete(VideoTraining training);

    void deleteByVideoId(Integer videoId);

    Long totalCount();

}