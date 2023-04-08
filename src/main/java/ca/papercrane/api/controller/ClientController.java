package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.ClientServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientServiceImpl clientService;

    @PostConstruct
    public void init() {
        createFakeClients();
        System.out.println("Fake clients created view at: http://localhost:8080/api/v1/clients/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> getAll() {
        try {
            return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Client> getUser(@PathVariable Integer userId) {
        try {
            val client = clientService.getByUserId(userId);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeClients() {
        clientService.addNewClient("client1@email.com", "123456", "Client Name 1", "Website");
        clientService.addNewClient("client2@email.com", "123456", "Client Name 2", "Website");
        clientService.addNewClient("client3@email.com", "123456", "Client Name 3", "Website");
        clientService.addNewClient("client4@email.com", "123456", "Client Name 4", "Website");
    }

}