package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.exceptions.ClientIdException;
import com.webproject.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveOrUpdateClient(Client client) {
        try {
            client.setPhoneNumber(client.getPhoneNumber().toUpperCase());
            return clientRepository.save(client);
        }catch (Exception e) {
            throw new ClientIdException("Client with number'" + client.getPhoneNumber().toUpperCase() + "' already exists");
        }
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        Client client = clientRepository.findByPhoneNumber(phoneNumber.toUpperCase());

        if(client == null) {
            throw new ClientIdException("Client with number '" + phoneNumber + "' does not exists");
        }
        return client;
    }

    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientsByIdentifier(String phoneNumber) {
        Client client = clientRepository.findByPhoneNumber(phoneNumber.toUpperCase());

        if(client == null) {
            throw new ClientIdException("Cannot Client with number '" + phoneNumber + "'. This client does not exist");
        }
        clientRepository.delete(client);
    }
}
