package ca.papercrane.api.service;

import ca.papercrane.api.request.PTORequest;

public interface PTORequestService {

    PTORequest findById(Integer timeOffId);

    void save(PTORequest request);

    Long totalCount();

}