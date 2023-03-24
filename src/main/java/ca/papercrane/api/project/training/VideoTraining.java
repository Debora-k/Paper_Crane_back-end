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

    }

    public VideoTraining(Integer projectId, String videoLink, String description) {
        this.projectId = projectId;
        this.videoLink = videoLink;
        this.description = description;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String newVideoLink) {
        this.videoLink = newVideoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}