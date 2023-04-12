package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.entity.role.EmployeeRole;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.AdminServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admins")
public class AdminController {

    private final AdminServiceImpl adminService;

    @PostConstruct
    public void init() {
        createFakeAdmins();
        System.out.println("Fake admins created view at: http://localhost:8080/api/v1/admins/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Admin>> getAll() {
        try {
            val adminList = adminService.getAll();
            return new ResponseEntity<>(adminList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Admin> getUser(@PathVariable Integer userId) {
        try {
            val admin = adminService.getByUserId(userId);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeAdmins() {
        adminService.addNewAdmin(new Admin("admin1@email.com", "123456", "Admin", "#1", EmployeeRole.DESIGNER));
        adminService.addNewAdmin(new Admin("admin2@email.com", "123456", "Admin", "#2", EmployeeRole.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin3@email.com", "123456", "Admin", "#3", EmployeeRole.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin4@email.com", "123456", "Admin", "#4", EmployeeRole.DESIGNER));
        adminService.addNewAdmin(new Admin("admin5email.com", "123456", "Admin", "#5", EmployeeRole.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin6@email.com", "123456", "Admin", "#6", EmployeeRole.DESIGNER));
        adminService.addNewAdmin(new Admin("admin7@email.com", "123456", "Admin", "#7", EmployeeRole.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin8@email.com", "123456", "Admin", "#8", EmployeeRole.DEVELOPER));
    }

}