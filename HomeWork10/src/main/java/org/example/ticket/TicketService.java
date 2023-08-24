package org.example.ticket;

import org.example.exception.NullOutputException;

import java.util.List;

public interface TicketService {



    Ticket getTicket(long id) throws NullOutputException;
    void updateTicket(long ticketId, String fromPlanet, String toPlanet) throws NullOutputException;
    List<Ticket> getAllTicket();
    void createNewTicket(String createdAt,long client_id, String fromPlanet, String toPlanet) throws NullOutputException;
    void deleteTicket( long ticketId) throws NullOutputException;
}
