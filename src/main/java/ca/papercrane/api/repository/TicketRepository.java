package ca.papercrane.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.papercrane.api.project.ticket.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional <Ticket> findByTicketId(Integer ticketId);
    List<Ticket> findAllByProjectId(Integer projectId);
    List<Ticket> findByStatus(char status);

}
