package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getByUserId(Integer userId) {
        return clientRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + userId));
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client not found with email: " + email));
    }

    @Override
    public Integer create(String email, String password, String clientName, String website) {
        final Client createdClient = clientRepository.save(new Client(email, password, clientName, website));
        return createdClient.getUserId();
    }

    @Override
    public Integer create(Client client) {
        final Client createdClient = clientRepository.save(client);
        return createdClient.getUserId();
    }

    @Override
    public void update(Client client) {
        final Client existingClient = getByUserId(client.getUserId());
        existingClient.setClientName(client.getClientName());
        existingClient.setWebsite(client.getWebsite());
        existingClient.setEmail(client.getEmail());
        existingClient.setType(client.getType());
        existingClient.setPassword(client.getPassword());
        save(existingClient);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        final Client client = getByUserId(userId);
        clientRepository.delete(client);
    }

    @Override
    public Long totalCount() {
        return clientRepository.count();
    }

}