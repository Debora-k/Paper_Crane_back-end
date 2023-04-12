package ca.papercrane.api.repository;

import ca.papercrane.api.request.TimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, Integer> {

    Optional<TimeOffRequest> findByTimeOffId(Integer timeOffId);

    Optional<List<TimeOffRequest>> findAllByEmployeeId(Integer userId);

}