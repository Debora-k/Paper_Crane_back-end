package ca.papercrane.api.controller;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.UserServiceImpl;
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
@RequestMapping("api/v1/users/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostConstruct
    public void init() {
        createFakeUsers();
        System.out.println("Fake users created view at: http://localhost:8080/api/v1/users/1");
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            final User User = userService.getByUserId(id);
            return new ResponseEntity<>(User, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeUsers() {
        userService.addNewUser(new User("user", "user1@email.ca", "123456"));
        userService.addNewUser(new User("user", "user2@email.ca", "123456"));
        userService.addNewUser(new User("user", "user3@email.ca", "123456"));
        userService.addNewUser(new User("user", "user4@email.ca", "123456"));
    }

}