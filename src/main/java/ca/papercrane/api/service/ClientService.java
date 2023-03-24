package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;

public interface ClientService {

    Client getByUserId(Integer userId);

    Client getByEmail(String email);

    void addNewClient(Client client);

    void updateClient(Integer userId, String name, String website, String email, String password);

    void save(Client client);

    void delete(Client client);

    Long totalCount();

}
