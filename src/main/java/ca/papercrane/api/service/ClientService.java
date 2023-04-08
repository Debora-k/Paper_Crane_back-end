package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getByUserId(Integer userId);

    Client getByEmail(String email);

    void addNewClient(String email, String password, String clientName, String companyName);

    void updateClient(Integer userId, String name, String website, String email, String password);

    void save(Client client);

    void delete(Client client);

    Long totalCount();

}
