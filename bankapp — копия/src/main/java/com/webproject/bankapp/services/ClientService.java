package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Account;
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
            client.setCreditCardNumber(client.getCreditCardNumber().toUpperCase());
            return clientRepository.save(client);
        }catch (Exception e) {
            throw new ClientIdException("Client with credit card number '"
                    + client.getCreditCardNumber().toUpperCase() + "' already exists");
        }
    }

    public Client findClientByCreditCardNumber(String creditCardNumber) {
        Client client = clientRepository.findByCreditCardNumber(creditCardNumber.toUpperCase());

        if(client == null) {
            throw new ClientIdException("Client with credit card number '"
                    + creditCardNumber + "' does not exists");
        }
        return client;
    }

    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientsByCreditCardNumber(String creditCardNumber) {
        Client client = clientRepository.findByCreditCardNumber(creditCardNumber.toUpperCase());

        if(client == null) {
            throw new ClientIdException("Cannot Client with credit card number '"
                    + creditCardNumber + "'. This client does not exist");
        }
        clientRepository.delete(client);
    }
}
