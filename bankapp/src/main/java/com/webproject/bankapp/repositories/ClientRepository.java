package com.webproject.bankapp.repositories;

import com.webproject.bankapp.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByCreditCardNumber(String creditCardNumber);

    @Override
    Iterable<Client> findAll();
}
