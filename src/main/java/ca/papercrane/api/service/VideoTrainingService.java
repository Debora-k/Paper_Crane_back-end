package ca.papercrane.api.service;

import ca.papercrane.api.project.training.VideoTraining;

public interface VideoTrainingService {

    VideoTraining getByVideoId(Integer videoId);

    Integer create(Integer projectId, String videoLink, String description);

    void update(Integer videoId, String description, String videoLink);

    void save(VideoTraining training);

    void saveByVideoId(Integer videoId);

    void deleteByVideoId(Integer videoId);

    Long totalCount();

}