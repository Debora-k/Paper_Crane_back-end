package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.TimeOffRequestRepository;
import ca.papercrane.api.request.TimeOffRequest;
import ca.papercrane.api.service.TimeOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public final class TimeOffRequestServiceImpl implements TimeOffService {

    @Autowired
    private TimeOffRequestRepository requestRepository;

    @Override
    public TimeOffRequest getByTimeOffId(Integer timeOffId) {
        return requestRepository.findByTimeOffId(timeOffId).orElseThrow(() -> new ResourceNotFoundException("TimeOffRequest not found with id: " + timeOffId));
    }

    @Override
    public List<TimeOffRequest> getAllByEmployeeId(Integer employeeId) {
        return requestRepository.findAllByEmployeeId(employeeId).orElseThrow(() -> new ResourceNotFoundException("No TimeOffRequests found for id: " + employeeId));
    }

    @Override
    public Integer create(Integer employeeId, Date startDate, Date endDate, String reason) {
        final TimeOffRequest createdRequest = requestRepository.save(new TimeOffRequest(employeeId, startDate, endDate, reason));
        return createdRequest.getTimeOffId();
    }

    @Override
    public Integer create(TimeOffRequest request) {
        final TimeOffRequest createdRequest = requestRepository.save(request);
        return createdRequest.getTimeOffId();
    }

    @Override
    public void update(TimeOffRequest request) {
        final TimeOffRequest existingRequest = getByTimeOffId(request.getTimeOffId());
        existingRequest.setReason(request.getReason());
        existingRequest.setStatus(request.getStatus());
        existingRequest.setStartDate(request.getStartDate());
        existingRequest.setEndDate(request.getEndDate());
        save(existingRequest);
    }

    @Override
    public void save(TimeOffRequest request) {
        requestRepository.save(request);
    }

    @Override
    public void delete(TimeOffRequest request) {
        requestRepository.delete(request);
    }

    @Override
    public void deleteByTimeOffId(Integer timeOffId) {
        final TimeOffRequest request = getByTimeOffId(timeOffId);
        requestRepository.delete(request);
    }

    @Override
    public Long totalCount() {
        return requestRepository.count();
    }

}