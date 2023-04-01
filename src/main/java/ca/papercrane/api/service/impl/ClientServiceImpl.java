package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

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
    public void addNewClient(Client client) {
        final Optional<Client> clientOptional = clientRepository.findByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void updateClient(Integer userId, String name, String company, String email, String password) {
        final Client client = getByUserId(userId);
        if (name != null && name.length() > 0 && !Objects.equals(client.getClientName(), name)) {
            client.setClientName(name);
        }
        if (company != null && company.length() > 0 && !Objects.equals(client.getCompany(), company)) {
            client.setCompany(company);
        }
        if (email != null && email.length() > 0 && !Objects.equals(client.getEmail(), email)) {
            final Optional<Client> clientOptional = clientRepository.findByEmail(email);
            if (clientOptional.isPresent()) {
                throw new IllegalArgumentException("Email is already taken.");
            }
            client.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(client.getPassword(), password)) {
            client.setPassword(password);
        }
        save(client);
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
    public Long totalCount() {
        return clientRepository.count();
    }

}