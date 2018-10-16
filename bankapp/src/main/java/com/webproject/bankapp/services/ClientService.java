package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveOrUpdateClient(Client client) {

        //Logical

        return clientRepository.save(client);
    }
}
