package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.PTORequestRepository;
import ca.papercrane.api.request.PTORequest;
import ca.papercrane.api.service.PTORequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PTORequestServiceImpl implements PTORequestService {

    @Autowired
    private PTORequestRepository ptoRequestRepository;

    @Override
    public PTORequest findById(Integer timeOffId) {
        return ptoRequestRepository.findById(timeOffId).orElseThrow(() -> new ResourceNotFoundException("PTORequest not found!"));
    }

    @Override
    public void save(PTORequest request) {
        ptoRequestRepository.save(request);
    }

    @Override
    public Long totalCount() {
        return ptoRequestRepository.count();
    }

}