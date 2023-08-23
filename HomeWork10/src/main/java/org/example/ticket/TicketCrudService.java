package org.example.ticket;

import org.example.client.Client;
import org.example.exception.NullOutputException;
import org.example.hibernateUtil.HibernateUtil;
import org.example.planet.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService implements TicketService{
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    @Override
    public void createNewTicket(long clientId, String fromPlanet, String toPlanet) {
        if (!fromPlanet.matches("^[A-Z0-9]*$") || !toPlanet.matches("^[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else {
            try(Session session = hibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                Client existingClient = session.get(Client.class, clientId);
                Planet existingFromPlanet = session.get(Planet.class, fromPlanet);
                Planet existingToPlanet = session.get(Planet.class, toPlanet);

                if (existingClient == null) {
                    System.out.println("Client with ID " + clientId + " does not exist.");
                } else if (existingFromPlanet == null) {
                    System.out.println("Planet with ID " + fromPlanet + " does not exist.");

                }
                else if (existingToPlanet == null) {
                    System.out.println("Planet with ID " + toPlanet + " does not exist.");

                }else {
                    Ticket newTicket = new Ticket();
                    newTicket.setClient(existingClient);
                    newTicket.setFromPlanet(existingFromPlanet);
                    newTicket.setToPlanet(existingToPlanet);
                    session.persist(newTicket);

                    System.out.println("The new ticket has been created.\n" + newTicket);

                    transaction.commit();
                }
            }
        }
    }
    @Override
    public void getTicket(long id) throws NullOutputException {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, id);
        System.out.println("ticket = " + ticket);
        session.close();
    }

    @Override
    public void updateTicket(long ticketId, String fromPlanet, String toPlanet) throws NullOutputException {
        if (!fromPlanet.matches("^[A-Z0-9]*$") || !toPlanet.matches("^[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else {
            try(Session session = hibernateUtil.getSessionFactory().openSession();) {
                Transaction transaction = session.beginTransaction();

                Ticket existing = session.get(Ticket.class, ticketId);
                Planet newFromPlanet = session.get(Planet.class, fromPlanet);
                Planet newToPlanet = session.get(Planet.class, toPlanet);

                if (existing == null) {
                    throw  new NullOutputException("Ticket with number: " + ticketId + " not found ");
                } else if (newFromPlanet == null) {
                    throw  new NullOutputException("Planet: " + fromPlanet + " not found ");
                }else if (newToPlanet == null) {
                    throw  new NullOutputException("Planet: " + toPlanet + " not found ");
                } else {
                    existing.setToPlanet(newToPlanet);
                    existing.setFromPlanet(newFromPlanet);
                    System.out.println("Successfully updated the ticket");
                    transaction.commit();
                }
            }
        }
    }

    public List<Ticket> getAllTicket(){
        Session session = hibernateUtil.getSessionFactory().openSession();
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
            System.out.println("passengers = " + tickets);
            session.close();
            return tickets;
    }



    @Override
    public void deleteTicket(long ticketId) throws NullOutputException {
        try(Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, ticketId);
            if (existing == null) {
                throw new NullOutputException("The client with name " + ticketId + " does not exists.");
            }
            session.remove(existing);

            transaction.commit();
            System.out.println("Ticket Removed ");
        }
    }
}
