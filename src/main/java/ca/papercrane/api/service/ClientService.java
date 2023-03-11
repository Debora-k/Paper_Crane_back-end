package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;

public interface ClientService {

    Client getByUserId(Integer userId);

    Client getByEmail(String email);

    Integer create(String email, String password, String clientName, String website);

    Integer create(Client client);

    void update(Client client);

    void save(Client client);

    void delete(Client client);

    void deleteByUserId(Integer userId);

    Long totalCount();

}
