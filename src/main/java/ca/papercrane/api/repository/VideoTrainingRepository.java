package ca.papercrane.api.repository;

import ca.papercrane.api.project.training.VideoTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoTrainingRepository extends JpaRepository<VideoTraining, Integer> {

    Optional<VideoTraining> findById(Integer videoId);

}