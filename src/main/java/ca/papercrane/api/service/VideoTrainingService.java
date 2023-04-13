package ca.papercrane.api.service;

import ca.papercrane.api.project.training.VideoTraining;

import java.util.List;

public interface VideoTrainingService {

    List<VideoTraining> getAll();

    VideoTraining getByVideoId(Integer videoId);

    Integer create(Integer projectId, String videoLink, String description);

    void update(Integer videoId, VideoTraining training);

    void save(VideoTraining training);

    void saveByVideoId(Integer videoId);

    void deleteByVideoId(Integer videoId);

    Long totalCount();

}