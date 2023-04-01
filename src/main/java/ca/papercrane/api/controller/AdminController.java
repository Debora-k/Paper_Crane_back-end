package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.AdminServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins/")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostConstruct
    public void init() {
        createFakeAdmins();
        System.out.println("Fake admins created view at: http://localhost:8080/api/v1/admins/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Admin>> getAll() {
        try {
            return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Admin> getUser(@PathVariable Integer id) {
        try {
            final Admin admin = adminService.getByUserId(id);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeAdmins() {
        adminService.addNewAdmin(new Admin("admin1@email.com", "123456", "Admin Name 1"));
        adminService.addNewAdmin(new Admin("admin2@email.com", "123456", "Admin Name 2"));
        adminService.addNewAdmin(new Admin("admin3@email.com", "123456", "Admin Name 3"));
        adminService.addNewAdmin(new Admin("admin4@email.com", "123456", "Admin Name 4"));
        adminService.addNewAdmin(new Admin("admin5email.com", "123456", "Admin Name 5"));
        adminService.addNewAdmin(new Admin("admin6@email.com", "123456", "Admin Name 6"));
        adminService.addNewAdmin(new Admin("admin7@email.com", "123456", "Admin Name 7"));
        adminService.addNewAdmin(new Admin("admin8@email.com", "123456", "Admin Name 8"));
    }

}