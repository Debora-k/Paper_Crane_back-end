package ca.papercrane.api.project.task;

import jakarta.persistence.*;

import java.util.Date;

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


    //TODO: These variables below do not exist in the database yet, but should probably be added.
    private String taskName;
    private Date dateCompleted;
    private boolean isComplete;

    public Task() {

    }

    public Task(Integer projectId, String description, Date startDate, Date deadline, Double expectedWorkHours) {
        this.projectId = projectId;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.expectedWorkHours = expectedWorkHours;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date newDeadline) {
        this.deadline = newDeadline;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date newStartDate) {
        startDate = newStartDate;
    }

    public Double getExpectedWorkHours() {
        return expectedWorkHours;
    }

    public void setExpectedWorkHours(Double newExpectedWorkHours) {
        expectedWorkHours = newExpectedWorkHours;
    }

    public Double getProgressInWorkHours() {
        return progressInWorkHours;
    }

    public void setProgressInWorkHours(Double newProgressHours) {
        progressInWorkHours = newProgressHours;
    }

    public void addProgressHours(Double amount) {
        progressInWorkHours += amount;
    }

    public void setName(String newName) {
        this.taskName = newName;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setCompleted() {
        this.isComplete = true;
    }

    public void setUncompleted() {
        this.isComplete = false;
    }

    public Date getCompletedDate() {
        return dateCompleted;
    }

    public void setCompletedDate(Date newDateCompleted) {
        this.dateCompleted = newDateCompleted;
    }

}