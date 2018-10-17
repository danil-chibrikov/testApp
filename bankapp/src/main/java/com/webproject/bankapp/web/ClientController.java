package com.webproject.bankapp.web;

import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.services.ClientService;
import com.webproject.bankapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewClient(@Valid @RequestBody Client client, BindingResult bindingResult) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null) return errorMap;

        Client cl = clientService.saveOrUpdateClient(client);
        return new ResponseEntity<Client>(cl, HttpStatus.CREATED);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getClientByPhoneNumber(@PathVariable String phoneNumber) {

        Client client = clientService.findClientByPhoneNumber(phoneNumber);

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<?> deleteClient(@PathVariable String phoneNumber) {
        clientService.deleteClientsByIdentifier(phoneNumber);

        return new ResponseEntity<String>("Account with ID: '" + phoneNumber + "' was deleted", HttpStatus.OK);
    }
}
