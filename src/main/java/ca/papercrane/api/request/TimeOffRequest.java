package ca.papercrane.api.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private char status;

    @Column(name = "reason", length = 500, nullable = false)
    private String reason;

    public TimeOffRequest(Integer employeeId, Date startDate, Date endDate, String reason) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = 'O';
        this.reason = reason;
    }

}