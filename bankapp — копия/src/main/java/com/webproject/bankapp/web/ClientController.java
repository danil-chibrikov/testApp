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

    @GetMapping("/{cardNumber}")
    public ResponseEntity<?> getClientByCardNumber(@PathVariable String cardNumber) {
        Client client = clientService.findClientByCardNumber(cardNumber);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<?> deleteClient(@PathVariable String cardNumber) {
        clientService.deleteClientsByCardNumber(cardNumber);
        return new ResponseEntity<String>("Client with card number: '" +
                cardNumber + "' was deleted", HttpStatus.OK);
    }
}
