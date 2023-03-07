package ca.papercrane.api.request;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "time_off")
public final class PTORequest {

    @Id
    @Column(name = "time_off_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeOffId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "reason", length = 500)
    private String reason;

    public PTORequest() {

    }

    public PTORequest(Integer employeeId, Date startDate, Date endDate, String reason) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "O"; //Setting default to "O" not sure what we are using for this.
        this.reason = reason;
    }

    public Integer getTimeOffId() {
        return timeOffId;
    }

    public void setTimeOffId(Integer timeOffId) {
        this.timeOffId = timeOffId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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