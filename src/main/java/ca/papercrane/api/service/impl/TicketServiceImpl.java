package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.ticket.Ticket;
import ca.papercrane.api.repository.TicketRepository;
import ca.papercrane.api.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public Ticket createTicket(Integer projectId, String title, String description) {
        Ticket ticket = new Ticket();
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets(Integer projectId) {
        List<Ticket> tickets = ticketRepository.findAllByProjectId(projectId);
        if(tickets.isEmpty()){
            throw new ResourceNotFoundException("No Tickets found for projectId: " + projectId);
        }
        return tickets;
    }



    @Override
    public List<Ticket> getTicketsByTitleAndProjectId(String title, Integer projectId) {
        return ticketRepository.findByTitleAndProjectId(title, projectId)
                .orElseThrow(() -> new RuntimeException("Tickets not found"));
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticket.getTicketId());
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setTitle(ticket.getTitle());
            existingTicket.setDescription(ticket.getDescription());
            existingTicket.setPriority(ticket.getPriority());
            existingTicket.setStatus(ticket.getStatus());
            return ticketRepository.save(existingTicket);
        } else {
            throw new RuntimeException("Ticket not found with id " + ticket.getTicketId());
        }
    }

    
    @Override
	public void deleteTicket(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
		
	}

	@Override
	public Long totalCount() {
		return ticketRepository.count();
	}


	
}
