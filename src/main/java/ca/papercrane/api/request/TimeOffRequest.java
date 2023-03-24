package ca.papercrane.api.request;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "time_off")
public final class TimeOffRequest {

    @Id
    @Column(name = "time_off_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeOffId;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status", length = 1, nullable = false)
    private String status;

    @Column(name = "reason", length = 500, nullable = false)
    private String reason;

    public TimeOffRequest() {

    }

    public TimeOffRequest(Integer employeeId, Date startDate, Date endDate, String reason) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "O";
        this.reason = reason;
    }

    public Integer getTimeOffId() {
        return timeOffId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}