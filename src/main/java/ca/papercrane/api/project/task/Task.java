package ca.papercrane.api.project.task;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a task in which is to completed within a Project.
 */
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

    /**
     * TODO: These variables below do not exist in the database yet, but should probably be added.
     */
    private String taskName;
    private Date dateCompleted;
    private boolean isComplete;

    public Task() {
        // default no-args constructor.
    }

    /**
     * Constructs a new Task.
     *
     * @param projectId         The project identifier this task will be listed under.
     * @param description       The description of what this task will require to be completed.
     * @param deadline          The provided date in which this task should be completed by.
     * @param startDate         The date that this task was started.
     * @param expectedWorkHours The guessed amount of hours that this task will task to finish.
     */
    public Task(Integer projectId, String description, Date deadline, Date startDate, Double expectedWorkHours) {
        this.projectId = projectId;
        this.description = description;
        this.deadline = deadline;
        this.startDate = startDate;
        this.expectedWorkHours = expectedWorkHours;
    }

    /**
     * Gets the generated task identifier value.
     *
     * @return The id of this task.
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * Gets the project id that this task belongs to.
     *
     * @return The project id.
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Gets the description of this Task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this task.
     *
     * @param newDescription The new description of what this task requires.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets the date in which this project is due to be completed.
     *
     * @return The deadline date.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets a new deadline for this task.
     *
     * @param newDeadline The new date in which this task is to be completed by.
     */
    public void setDeadline(Date newDeadline) {
        this.deadline = newDeadline;
    }

    /**
     * Gets the date of when this task was created.
     *
     * @return The start date of the task.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the date in which this task was created.
     *
     * @param newStartDate The date this task was started.
     */
    public void setStartDate(Date newStartDate) {
        startDate = newStartDate;
    }

    /**
     * Gets the guessed-amount of hours that this task will take to reach completion.
     *
     * @return The hour amount.
     */
    public Double getExpectedWorkHours() {
        return expectedWorkHours;
    }

    /**
     * Sets the amount of hours that this task will take to reach completion.
     *
     * @param newExpectedWorkHours The guessed-amount of hours.
     */
    public void setExpectedWorkHours(Double newExpectedWorkHours) {
        expectedWorkHours = newExpectedWorkHours;
    }

    /**
     * Gets the current amount of logged hours towards this task.
     *
     * @return The hour amount.
     */
    public Double getProgressInWorkHours() {
        return progressInWorkHours;
    }

    /**
     * Sets a new total for the amount of logged hours towards this task.
     *
     * @param newProgressHours The new amount of hours logged towards this task.
     */
    public void setProgressInWorkHours(Double newProgressHours) {
        progressInWorkHours = newProgressHours;
    }

    /**
     * Adds an amount of hours to log towards the task.
     *
     * @param amount The amount of hours to log towards task completion.
     */
    public void addProgressHours(Double amount) {
        progressInWorkHours += amount;
    }

    /**
     * Sets the name of this task.
     *
     * @param newName The new name for this task.
     */
    public void setName(String newName) {
        this.taskName = newName;
    }

    //check to see if task is complete
    public boolean isComplete() {
        return isComplete;
    }

    //sets the Task to a completed state.
    public void setCompleted() {
        this.isComplete = true;
    }

    //sets the Task to an uncompleted state.
    public void setUncompleted() {
        this.isComplete = false;
    }

    //get the date the task was completed
    public Date getCompletedDate() {
        return dateCompleted;
    }

    //set the date that the task was completed
    public void setCompletedDate(Date newDateCompleted) {
        this.dateCompleted = newDateCompleted;
    }

}