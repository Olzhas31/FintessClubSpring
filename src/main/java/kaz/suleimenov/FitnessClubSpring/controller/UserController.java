package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.UserNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.User;
import kaz.suleimenov.FitnessClubSpring.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллера для аккаунтов, где задаются HTTP запросы.
 */
@RestController
public class UserController {
    
    private final UserRepository userRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param userRepository - репозиторий аккаунтов
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * GET запрос
     * @return все аккаунты
     */
    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    /**
     * POST запрос
     * @param user - новый аккаунт
     * @return новый аккаунт
     */
    @PostMapping("/users")
    User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * GET запрос определенного аккаунта
     * @param id - id аккаунта
     * @return аккаунт по id
     */
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * PUT запрос на изменение аккаунта
     * @param user - новый аккаунт
     * @param id - id аккаунта
     * @return измененный аккаунт
     */
    @PutMapping("/users/{id}")
    User replaceClient(@RequestBody User user, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(area -> {
                    user.setLogin(user.getLogin());
                    user.setPassword(user.getPassword());
                    return userRepository.save(area);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    /**
     * DELETE запрос
     * @param id - id аккаунта
     */
    @DeleteMapping("/users/{id}")
    void deleteClient(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
