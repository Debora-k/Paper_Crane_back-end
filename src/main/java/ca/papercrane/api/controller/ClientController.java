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

    /**
     * Gets a list of all Clients.
     *
     * @return The list of all found clients.
     */
    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            val clientList = clientService.getAll();
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Client.
     *
     * @param client The new Client being created.
     * @return The new clients generated user id.
     */
    @PostMapping("/create")
    public ResponseEntity<Integer> createClient(@RequestBody Client client) {
        try {
            val createdClientId = clientService.addNewClient(client);
            return new ResponseEntity<>(createdClientId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a Client by their userId value.
     *
     * @param userId The clients user id.
     * @return The response status of the request.
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer userId) {
        try {
            clientService.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Client.
     *
     * @param client The new client details.
     * @return The response status of the request.
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<HttpStatus> updateClient(@PathVariable Integer userId, @RequestBody Client client) {
        try {
            clientService.update(userId, client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a Client user by their userId.
     *
     * @param userId The user id of the client being searched for.
     * @return The client user data found.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Client> getClient(@PathVariable Integer userId) {
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
        clientService.addNewClient(new Client("client1@email.com", "123456", "Client Name 1", "Website"));
        clientService.addNewClient(new Client("client2@email.com", "123456", "Client Name 2", "Website"));
        clientService.addNewClient(new Client("client3@email.com", "123456", "Client Name 3", "Website"));
        clientService.addNewClient(new Client("client4@email.com", "123456", "Client Name 4", "Website"));
    }

}