package org.example.ticket;

import org.example.exception.NullOutputException;

import java.util.List;

public interface TicketService {



    void getTicket(long id) throws NullOutputException;
    void updateTicket(long ticketId, String fromPlanet, String toPlanet) throws NullOutputException;
    List<Ticket> getAllTicket();
    void createNewTicket(long client_id, String fromPlanet, String toPlanet) throws NullOutputException;
    void deleteTicket( long ticketId) throws NullOutputException;
}
