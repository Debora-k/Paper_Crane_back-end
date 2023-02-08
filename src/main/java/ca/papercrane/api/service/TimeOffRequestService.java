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

}