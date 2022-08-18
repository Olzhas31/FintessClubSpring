package kaz.suleimenov.FitnessClubSpring.controller.errors;


public class SubscriptionsNotFoundException extends RuntimeException {
    public SubscriptionsNotFoundException(Long id) {
        super("Абонемент с id " + id + " не найдено.");
    }
}
