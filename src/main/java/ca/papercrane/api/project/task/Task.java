package ca.papercrane.api.project.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public final class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "expected_work_hours", nullable = false)
    private Double expectedWorkHours;

    @Column(name = "progress_in_work_hours", columnDefinition = "double precision default 0")
    private Double progressInWorkHours;

    @Column(name = "taskName", length = 50, nullable = false)
    private String taskName;

    @Column(name = "date_completed", nullable = false)
    private Date dateCompleted;

    @Column(name = "is_complete", nullable = false)
    private boolean isComplete;

    public Task(Integer projectId, String description, Date startDate, Date deadline, Double expectedWorkHours) {
        this.projectId = projectId;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.expectedWorkHours = expectedWorkHours;
    }

}