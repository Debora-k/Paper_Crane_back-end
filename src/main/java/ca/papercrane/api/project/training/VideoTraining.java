package ca.papercrane.api.project.training;

import jakarta.persistence.*;

@Entity
@Table(name = "video_training")
public final class VideoTraining {

    @Id
    @Column(name = "video_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;

    @Column(name = "video_link", nullable = false, length = 300)
    private String videoLink;

    //TODO: Add to database if we want to separate per-project.
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    //TODO: Add to database if we want a description of the training video.
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    public VideoTraining() {
        //default no-arg constructor.
    }

    /**
     * Constructs a new video training object.
     *
     * @param projectId   The id of the project this video is for.
     * @param videoLink   A link to the training video
     * @param description A description of the training video
     */
    public VideoTraining(Integer projectId, String videoLink, String description) {
        this.projectId = projectId;
        this.videoLink = videoLink;
        this.description = description;
    }

    /**
     * Gets the project id that this video training belongs to.
     *
     * @return The project id.
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Gets the video id number.
     *
     * @return The video id number.
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * Gets the link to the video.
     *
     * @return The link to the video.
     */
    public String getVideoLink() {
        return videoLink;
    }

    /**
     * Changes the link to this video.
     *
     * @param newVideoLink The new link to the video
     */
    public void setVideoLink(String newVideoLink) {
        this.videoLink = newVideoLink;
    }

    /**
     * Gets the description of this video.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this video.
     *
     * @param newDescription The new description of what this video requires.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}