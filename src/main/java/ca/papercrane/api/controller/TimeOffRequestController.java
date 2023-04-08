package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.request.TimeOffRequest;
import ca.papercrane.api.service.impl.TimeOffRequestServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/time_off_requests")
public class TimeOffRequestController {

    private final TimeOffRequestServiceImpl requestService;

    @PostConstruct
    public void init() {
        createFakeTimeOffRequest();
        System.out.println("Fake request created view at: http://localhost:8080/api/v1/time_off_requests/1");
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<TimeOffRequest> getRequest(@PathVariable Integer requestId) {
        try {
            val request = requestService.getByTimeOffId(requestId);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeTimeOffRequest() {
        val startDate = new Date(2020, 10, 1);
        val endDate = new Date(2020, 10, 14);
        val request = new TimeOffRequest(1, startDate, endDate, "Vacation");
        requestService.save(request);
    }

}