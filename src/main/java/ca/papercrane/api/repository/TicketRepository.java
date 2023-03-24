package ca.papercrane.api.repository;

<<<<<<< Updated upstream
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
import ca.papercrane.api.project.task.Task;
>>>>>>> Stashed changes
import ca.papercrane.api.project.ticket.Ticket;

import java.util.List;
import java.util.Optional;

<<<<<<< Updated upstream
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional <Ticket> findByTicketId(Integer ticketId);
    List<Ticket> findAllByProjectId(Integer projectId);
    List<Ticket> findByStatus(char status);

}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Optional<Task> findByTicketId(Integer ticketId);
	
	List<Ticket> findAllByProjectId(Integer projectId);

    Optional<List<Ticket>> findByTitleAndProjectId(String title, Integer projectId);
}

>>>>>>> Stashed changes
