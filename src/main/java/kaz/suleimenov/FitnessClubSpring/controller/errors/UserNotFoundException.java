package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Пользователь с id " + id + " не найден.");
    }
}
