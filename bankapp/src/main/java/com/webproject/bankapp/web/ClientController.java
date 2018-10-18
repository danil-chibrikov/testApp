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

    @GetMapping("/{creditCardNumber}")
    public ResponseEntity<?> getClientByCreditCardNumber(@PathVariable String creditCardNumber) {

        Client client = clientService.findClientByCreditCardNumber(creditCardNumber);

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @DeleteMapping("/{creditCardNumber}")
    public ResponseEntity<?> deleteClient(@PathVariable String creditCardNumber) {
        clientService.deleteClientsByCreditCardNumber(creditCardNumber);

        return new ResponseEntity<String>("Client with credit card number: '" +
                creditCardNumber + "' was deleted", HttpStatus.OK);
    }
}
