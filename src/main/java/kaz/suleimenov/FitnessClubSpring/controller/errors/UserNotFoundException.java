package kaz.suleimenov.FitnessClubSpring.controller.errors;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Пользователь с id " + id + " не найден.");
    }
}
