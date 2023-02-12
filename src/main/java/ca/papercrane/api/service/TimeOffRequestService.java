package ca.papercrane.api.service;

import ca.papercrane.api.repository.TimeOffRequestRepository;
import ca.papercrane.api.request.TimeOffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving and altering data in the {@link TimeOffRequestRepository}
 */
@Service
public record TimeOffRequestService(TimeOffRequestRepository timeOffRepository) {

    @Autowired
    public TimeOffRequestService {
    }

    /**
     * Returns an Optional containing the TimeOffRequest that exists with the specified timeOffId.
     *
     * @param timeOffId The integer timeOffId being searched for.
     * @return An Optional containing the found TimeOffRequest, or an empty Optional if no TimeOffRequest is found with the specified timeOffId.
     */
    public Optional<TimeOffRequest> getByTimeOffId(Integer timeOffId) {
        return timeOffRepository.findByTimeOffId(timeOffId);
    }

    /**
     * Returns a List of TimeOffRequest objects that belong to the specified employeeId.
     *
     * @param employeeId The employeeId that the list of TimeOffRequest belong to.
     * @return The List of TimeOffRequest objects for the employee.
     */
    public List<TimeOffRequest> getAllByEmployeeId(Integer employeeId) {
        return timeOffRepository.findByEmployeeId(employeeId);
    }

    /**
     * Persists a TimeOffRequest into the database.
     *
     * @param timeOffRequest The TimeOffRequest being inserted into the database.
     * @return the TimeOffRequest object.
     */
    public TimeOffRequest persistTask(TimeOffRequest timeOffRequest) {

        //TODO: Check for the following before adding a TimeOffRequest to the database.

        //TODO: EmployeeId must not be null, EmployeeId must point to a valid employee saved within the database.

        //TODO: Start/End Dates must not be null, Dates must use correct format.

        //TODO: Reason must not be null, Reason must meet length or character requirements if any etc.

        return timeOffRepository.save(timeOffRequest);
    }

    /**
     * Deletes a TimeOffRequest from the database.
     *
     * @param timeOffRequest the request being deleted.
     */
    public void delete(TimeOffRequest timeOffRequest) {
        //TODO: Checks or validation before deleting.
        timeOffRepository.delete(timeOffRequest);
    }

    /**
     * Deletes a TimeOffRequest request by the specified requestId.
     *
     * @param timeOffId the id of the request being deleted.
     */
    public void deleteById(Integer timeOffId) {
        //TODO: Checks or validation before deleting.
        timeOffRepository.deleteById(timeOffId);
    }

    /**
     * Returns the total amount of time-off requests stored within the repository.
     *
     * @return the count.
     */
    public Long totalTimeOffRequestCount() {
        return timeOffRepository.count();
    }

}