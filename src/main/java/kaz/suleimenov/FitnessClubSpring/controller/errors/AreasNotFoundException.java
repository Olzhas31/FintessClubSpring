package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class AreasNotFoundException extends RuntimeException {
    public AreasNotFoundException(Long id) {
        super("Зал с id " + id + " не найден.");
    }
}
