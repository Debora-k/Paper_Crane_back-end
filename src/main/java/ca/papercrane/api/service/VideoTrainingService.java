package ca.papercrane.api.service;

import ca.papercrane.api.project.training.VideoTraining;
import ca.papercrane.api.repository.VideoTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * A Service class for retrieving and altering data in the {@link VideoTrainingRepository}
 */
@Service
@Transactional
public class VideoTrainingService {

    @Autowired
    private final VideoTrainingRepository videoTrainingRepository;

    /**
     * Creates a new VideoTrainingService.
     *
     * @param videoTrainingRepository The training repository.
     */
    public VideoTrainingService(VideoTrainingRepository videoTrainingRepository) {
        this.videoTrainingRepository = videoTrainingRepository;
    }

    /**
     * Creates a new VideoTraining.
     *
     * @param projectId   The project id the training is related to.
     * @param videoLink   The link to access the training.
     * @param description The description of the training.
     * @return The newly created and saved object.
     */
    public VideoTraining createTraining(Integer projectId, String videoLink, String description) {
        final VideoTraining training = new VideoTraining(projectId, videoLink, description);
        //TODO: Checks/Validation before saving.
        return saveTraining(training);
    }

    /**
     * Saves a training object into the database.
     *
     * @param training The training being saved into the database.
     * @return the training object.
     */
    public VideoTraining saveTraining(VideoTraining training) {
        //TODO: Checks/Validation before saving.
        return videoTrainingRepository.save(training);
    }

    /**
     * Updates a training record.
     *
     * @param training The training being updated.
     */
    public void updateTraining(VideoTraining training) {
        final Optional<VideoTraining> existingTrainingOptional = videoTrainingRepository.findByVideoId(training.getVideoId());
        if (existingTrainingOptional.isPresent()) {
            final VideoTraining existingTraining = existingTrainingOptional.get();
            existingTraining.setVideoLink(training.getVideoLink());
            existingTraining.setDescription(training.getDescription());
            saveTraining(existingTraining);
        } else {
            //TODO: Exception.
        }
    }

    /**
     * Deletes a training record from the database.
     *
     * @param training the training being deleted.
     */
    public void delete(VideoTraining training) {
        //TODO: Checks or validation before deleting.
        videoTrainingRepository.delete(training);
    }

    /**
     * Deletes training by the specified videoId.
     *
     * @param videoId the id of the training being deleted.
     */
    public void deleteById(Integer videoId) {
        //TODO: Checks or validation before deleting.
        videoTrainingRepository.deleteById(videoId);
    }

    /**
     * Returns an optional containing the training that exists with the specified videoId.
     *
     * @param videoId The videoId being searched for.
     * @return An optional containing the found training.
     */
    public Optional<VideoTraining> getByVideoId(Integer videoId) {
        return videoTrainingRepository.findByVideoId(videoId);
    }

    /**
     * Returns the total amount of video training records stored within the repository.
     *
     * @return the count.
     */
    public Long totalVideos() {
        return videoTrainingRepository.count();
    }

}