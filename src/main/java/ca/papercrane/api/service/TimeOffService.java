package ca.papercrane.api.service;

import ca.papercrane.api.request.TimeOffRequest;

import java.util.Date;
import java.util.List;

public interface TimeOffService {

    List<TimeOffRequest> getAll();

    TimeOffRequest getByTimeOffId(Integer timeOffId);

    List<TimeOffRequest> getAllByEmployeeId(Integer userId);

    Integer create(Integer employeeId, Date startDate, Date endDate, String reason);

    Integer create(TimeOffRequest request);

    void update(TimeOffRequest request);

    void save(TimeOffRequest request);

    void delete(TimeOffRequest request);

    void deleteByTimeOffId(Integer timeOffId);

    Long totalCount();

}