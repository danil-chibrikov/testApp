package com.webproject.bankapp.repositories;

import com.webproject.bankapp.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByCardNumber(String Identifier);
}