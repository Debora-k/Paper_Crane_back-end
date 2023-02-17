package ca.papercrane.api.repository;

import ca.papercrane.api.project.training.VideoTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoTrainingRepository extends JpaRepository<VideoTraining, Integer> {

    Optional<VideoTraining> findByVideoId(Integer videoId);

    List<VideoTraining> findAllByProjectId(Integer projectId);

}