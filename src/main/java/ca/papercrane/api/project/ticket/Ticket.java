package ca.papercrane.api.project.ticket;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public final class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private Integer ticketId;

    @Column(name = "projectId", nullable = false)
    private Integer projectId;

    @Column(name = "title", length =100 , nullable = false)
    private String title;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    private char priority;

    @Column(name = "status", nullable = false)
    private char status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "closed_at", nullable = false)
    private Date closedAt;

    public Ticket() {
        // default no-args constructor.

    }

    public Ticket(Integer projectId, String title, String description, char priority, char status, Date createdAt, Date closedAt) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }

    // getter and setter methods for all fields

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getPriority() {
        return priority;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }
}

