package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.UserAlreadyExistsException;
import kaz.suleimenov.FitnessClubSpring.controller.errors.UserNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.User;
import kaz.suleimenov.FitnessClubSpring.repository.UserRepository;
import kaz.suleimenov.FitnessClubSpring.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> all() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/users/{id}")
    public User replaceClient(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteClient(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
