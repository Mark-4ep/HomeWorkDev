package org.example.client;

import org.example.exception.NullOutputException;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService implements ClientService{
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();
    @Override
    public void createClient(Client client) throws NullOutputException {
        if (client.getName().length() <= 3) {
            System.out.println("A new client cannot be created due to the restrictions," +
                    " the name must have at least 2 characters.");
        } else {
            try(Session session = hibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();

                Client newClient = new Client();
                newClient.setName(client.getName());
                session.persist(newClient);
                System.out.println("The client with name " + client.getName() + " has been created.\n" + newClient);

                transaction.commit();

            }
        }
    }


    @Override
    public void deleteClient(long idClient) throws NullOutputException {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client existing = session.get(Client.class, idClient);
            if (existing == null) {
                throw new NullOutputException("The client with name " + existing.getName() + " does not exists.");
            } else {


                session.remove(existing);
                transaction.commit();

                System.out.println("A client named " + existing.getName() + " is shown!");
            }
        }
    }

    @Override
    public void getClient(long id) throws NullOutputException {
        try(Session session = hibernateUtil.getSessionFactory().openSession()) {
            Client client = session.get(Client.class , id);
            if (client == null) {
                throw new NullOutputException("No client found with the id :" + id);
            } else {
                System.out.println(client);
            }
        }
    }


    @Override
    public void updateClient(long id, Client client) throws NullOutputException {
        try(Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client oldClient = session.get(Client.class, id);
            if (client == null) {
                throw new NullOutputException("No client found with the id :" + id);
            } else {
                oldClient.setName(client.getName());
                session.persist(oldClient);
                System.out.println("Successfully updated the client");
                transaction.commit();

            }
        }
    }



    @Override
    public List<Client> getAllClients() {
        Session session = hibernateUtil.getSessionFactory().openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        System.out.println("passengers = " + clients);
        session.close();
        return clients;
    }
}
