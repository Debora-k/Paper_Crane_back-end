package ca.papercrane.api.project.training;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_training")
public final class VideoTraining {

    @Id
    @Column(name = "video_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;

    @Column(name = "video_project_id", nullable = false)
    private Integer projectId;

    @Column(name = "video_link", nullable = false, length = 300)
    private String videoLink;

    @Column(name = "video_description", nullable = false, length = 500)
    private String description;

    public VideoTraining(Integer projectId, String videoLink, String description) {
        this.projectId = projectId;
        this.videoLink = videoLink;
        this.description = description;
    }

}