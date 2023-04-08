package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAll() throws ResourceNotFoundException {
        val clientList = clientRepository.findAll();
        if (clientList.isEmpty()) {
            throw new ResourceNotFoundException("No clients found!");
        }
        return clientList;
    }

    @Override
    public Client getByUserId(Integer userId) {
        return clientRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + userId));
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client not found with email: " + email));
    }

    @Override
    public void addNewClient(String email, String password, String clientName, String companyName) {
        val clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        val client = new Client(email, password, clientName, companyName);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void updateClient(Integer userId, String email, String password, String clientName, String companyName) {
        val client = getByUserId(userId);
        if (clientName != null && clientName.length() > 0 && !Objects.equals(client.getClientName(), clientName)) {
            client.setClientName(clientName);
        }
        if (companyName != null && companyName.length() > 0 && !Objects.equals(client.getCompanyName(), companyName)) {
            client.setCompanyName(companyName);
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