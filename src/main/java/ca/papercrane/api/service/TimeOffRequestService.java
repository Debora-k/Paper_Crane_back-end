package ca.papercrane.api.service;

import ca.papercrane.api.repository.TimeOffRequestRepository;
import ca.papercrane.api.request.TimeOffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving and altering data in the {@link TimeOffRequestRepository}
 */
@Service
@Transactional
public class TimeOffRequestService {

    @Autowired
    private final TimeOffRequestRepository timeOffRequestRepository;

    /**
     * Creates a new TimeOffRequestService.
     *
     * @param timeOffRequestRepository The request repository.
     */
    public TimeOffRequestService(TimeOffRequestRepository timeOffRequestRepository) {
        this.timeOffRequestRepository = timeOffRequestRepository;
    }

    /**
     * Crates a new TimeOffRequest.
     *
     * @param employeeId The id of the employee making the request.
     * @param startDate  The date the time-off will start on.
     * @param endDate    The date the time-off will end on.
     * @param reason     The reason for the time-off request.
     * @return The newly created and saved request.
     */
    public TimeOffRequest createRequest(Integer employeeId, Date startDate, Date endDate, String reason) {
        final TimeOffRequest timeOffRequest = new TimeOffRequest(employeeId, startDate, endDate, reason);
        //TODO: Checks/Validation before saving.
        return saveRequest(timeOffRequest);
    }

    /**
     * Saves a request into the database.
     *
     * @param timeOffRequest The request being saved into the database.
     * @return the request object.
     */
    public TimeOffRequest saveRequest(TimeOffRequest timeOffRequest) {
        //TODO: Checks/Validation before saving.
        return timeOffRequestRepository.save(timeOffRequest);
    }

    /**
     * Updates a request.
     *
     * @param timeOffRequest The request being updated.
     */
    public void updateRequest(TimeOffRequest timeOffRequest) {
        final Optional<TimeOffRequest> existingRequestOptional = timeOffRequestRepository.findByTimeOffId(timeOffRequest.getTimeOffId());
        if (existingRequestOptional.isPresent()) {
            final TimeOffRequest existingRequest = existingRequestOptional.get();
            existingRequest.setEmployeeId(timeOffRequest.getEmployeeId());
            existingRequest.setStartDate(timeOffRequest.getStartDate());
            existingRequest.setEndDate(timeOffRequest.getEndDate());
            existingRequest.setReason(timeOffRequest.getReason());
            existingRequest.setStatus(timeOffRequest.getStatus());
            saveRequest(timeOffRequest);
        } else {
            //TODO: Exception.
        }
    }

    /**
     * Deletes a request from the database.
     *
     * @param timeOffRequest the request being deleted.
     */
    public void delete(TimeOffRequest timeOffRequest) {
        //TODO: Checks or validation before deleting.
        timeOffRequestRepository.delete(timeOffRequest);
    }

    /**
     * Deletes a request by the specified requestId.
     *
     * @param timeOffId the id of the request being deleted.
     */
    public void deleteById(Integer timeOffId) {
        //TODO: Checks or validation before deleting.
        timeOffRequestRepository.deleteById(timeOffId);
    }

    /**
     * Returns an optional containing the request that exists with the specified timeOffId.
     *
     * @param timeOffId The timeOffId being searched for.
     * @return An optional containing the found request.
     */
    public Optional<TimeOffRequest> getByTimeOffId(Integer timeOffId) {
        return timeOffRequestRepository.findByTimeOffId(timeOffId);
    }

    /**
     * Returns a list of request objects that belong to the specified employeeId.
     *
     * @param employeeId The employeeId that the list of requests belong to.
     * @return The list of request objects for the employee.
     */
    public List<TimeOffRequest> getAllByEmployeeId(Integer employeeId) {
        //TODO: Check the employeeId is valid.
        return timeOffRequestRepository.findByEmployeeId(employeeId);
    }

    /**
     * Returns the total amount of requests stored within the repository.
     *
     * @return the count.
     */
    public Long totalRequestCount() {
        return timeOffRequestRepository.count();
    }

}