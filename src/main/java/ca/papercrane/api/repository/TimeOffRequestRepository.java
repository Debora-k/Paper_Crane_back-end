package ca.papercrane.api.repository;

import ca.papercrane.api.request.TimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, Integer> {

    Optional<TimeOffRequest> findByTimeOffId(Integer timeOffId);

    List<TimeOffRequest> findByEmployeeId(Integer employeeId);

}