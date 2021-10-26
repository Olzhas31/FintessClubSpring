package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class SubscriptionsNotFoundException extends RuntimeException {
    public SubscriptionsNotFoundException(Long id) {
        super("Абонемент с id " + id + " не найдено.");
    }
}
