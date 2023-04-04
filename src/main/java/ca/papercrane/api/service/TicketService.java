package ca.papercrane.api.service;

import ca.papercrane.api.project.ticket.Ticket;

import java.util.List;

public interface TicketService {

    Ticket createTicket(Integer projectId, String title, String description);

    List <Ticket> getAllTickets(Integer projectId);

    List<Ticket> getTicketsByTitleAndProjectId(String title, Integer projectId);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Integer ticketId);
    
    Long totalCount();

}

