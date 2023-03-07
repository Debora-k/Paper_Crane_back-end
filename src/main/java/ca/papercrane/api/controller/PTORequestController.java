package ca.papercrane.api.controller;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.request.PTORequest;
import ca.papercrane.api.service.impl.PTORequestServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/pto_requests/")
public final class PTORequestController {

    @Autowired
    private PTORequestServiceImpl ptoRequestService;

    @PostConstruct
    public void init() {
        createFakePTORequest();
        System.out.println("Fake PTORequest created view at: http://localhost:8080/api/pto_requests/id/1");
    }

    @GetMapping("id/{id}")
    public ResponseEntity<PTORequest> getRequest(@PathVariable Integer id) {
        try {
            PTORequest request = ptoRequestService.findById(id);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakePTORequest() {
        final Date startDate = new Date(2020, 10, 1);
        final Date endDate = new Date(2020, 10, 14);
        PTORequest request = new PTORequest(1, startDate, endDate, "Vacation");
        ptoRequestService.save(request);
    }

}