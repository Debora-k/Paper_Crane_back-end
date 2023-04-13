package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.entity.role.EmployeeType;
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

    /**
     * Gets a list of all Admin employees.
     *
     * @return The list of admins.
     */
    @GetMapping("")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        try {
            val adminList = adminService.getAll();
            return new ResponseEntity<>(adminList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Admin.
     *
     * @param admin The new Admin being created.
     * @return The admins generated user id.
     */
    @PostMapping("/new")
    public ResponseEntity<Integer> createAdmin(@RequestBody Admin admin) {
        try {
            val createdAdminId = adminService.addNewAdmin(admin);
            return new ResponseEntity<>(createdAdminId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an Admin by their userId value.
     *
     * @param userId The admins user id.
     * @return The response status of the request.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Integer userId) {
        try {
            adminService.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Admin.
     *
     * @param admin The new admin details.
     * @return The response status of the request.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody Admin admin) {
        try {
            adminService.update(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets an Admin user by their userId.
     *
     * @param userId The user id of the admin being searched for.
     * @return The admin user data found.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Integer userId) {
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
        adminService.addNewAdmin(new Admin("admin1@email.com", "123456", "Admin", "#1", EmployeeType.DESIGNER));
        adminService.addNewAdmin(new Admin("admin2@email.com", "123456", "Admin", "#2", EmployeeType.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin3@email.com", "123456", "Admin", "#3", EmployeeType.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin4@email.com", "123456", "Admin", "#4", EmployeeType.DESIGNER));
        adminService.addNewAdmin(new Admin("admin5email.com", "123456", "Admin", "#5", EmployeeType.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin6@email.com", "123456", "Admin", "#6", EmployeeType.DESIGNER));
        adminService.addNewAdmin(new Admin("admin7@email.com", "123456", "Admin", "#7", EmployeeType.DEVELOPER));
        adminService.addNewAdmin(new Admin("admin8@email.com", "123456", "Admin", "#8", EmployeeType.DEVELOPER));
    }

}