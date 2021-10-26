package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class ActivitiesNotFoundException extends RuntimeException {
    public ActivitiesNotFoundException(Long id) {
        super("Занятие с id " + id + " не найдено.");
    }
}
