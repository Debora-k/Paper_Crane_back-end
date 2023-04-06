package ca.papercrane.api.controller;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostConstruct
    public void init() {
        createFakeUsers();
        System.out.println("Fake users created view at: http://localhost:8080/api/v1/users/1");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            val User = userService.getByUserId(id);
            return new ResponseEntity<>(User, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeUsers() {
        userService.addNewUser(new User("user1@email.ca", "123456"));
        userService.addNewUser(new User("user2@email.ca", "123456"));
        userService.addNewUser(new User("user3@email.ca", "123456"));
        userService.addNewUser(new User("user4@email.ca", "123456"));
    }

}