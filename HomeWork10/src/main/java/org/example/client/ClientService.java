package org.example.client;

import org.example.exception.NullOutputException;

import java.util.List;

public interface ClientService {

    void createClient(Client client) throws NullOutputException;
    void deleteClient(long id) throws NullOutputException;
    void getClient (long id) throws NullOutputException;
    void updateClient(long id, Client client) throws NullOutputException;
    List<Client> getAllClients();
}
