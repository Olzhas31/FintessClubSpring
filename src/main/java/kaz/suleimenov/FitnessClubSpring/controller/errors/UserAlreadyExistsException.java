package kaz.suleimenov.FitnessClubSpring.controller.errors;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String login) {
        super("Пользователь с таким логинем " + login + " уже существует.");
    }
}
