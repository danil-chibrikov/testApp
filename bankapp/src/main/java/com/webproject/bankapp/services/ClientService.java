package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Backlog;
import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.exceptions.ClientIdException;
import com.webproject.bankapp.repositories.BacklogRepository;
import com.webproject.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Client saveOrUpdateClient(Client client) {
        try {
            client.setCardNumber(client.getCardNumber().toUpperCase());
            if(client.getId() == null){
                Backlog backlog = new Backlog();
                client.setBacklog(backlog);
                backlog.setClient(client);
                backlog.setCardNumber(client.getCardNumber().toUpperCase());
            }
            if(client.getId() != null){
                client.setBacklog(backlogRepository.findByCardNumber(client.getCardNumber().toUpperCase()));
            }

            return clientRepository.save(client);
        }catch (Exception e) {
            throw new ClientIdException("Client with card number '"
                    + client.getCardNumber().toUpperCase() + "' already exists");
        }
    }

    public Client findClientByCardNumber(String cardNumber) {
        Client client = clientRepository.findByCardNumber(cardNumber.toUpperCase());
        if(client == null) {
            throw new ClientIdException("Client with card number '"
                    + cardNumber + "' does not exists");
        }
        return client;
    }

    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientsByCardNumber(String cardNumber) {
        Client client = clientRepository.findByCardNumber(cardNumber.toUpperCase());
        if(client == null) {
            throw new ClientIdException("Cannot Client with card number '"
                    + cardNumber + "'. This client does not exist");
        }
        clientRepository.delete(client);
    }
}
