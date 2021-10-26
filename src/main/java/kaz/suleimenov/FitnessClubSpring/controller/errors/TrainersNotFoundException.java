package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class TrainersNotFoundException extends RuntimeException {
    public TrainersNotFoundException(Long id) {
        super("Тренер с id " + id + " не найден.");
    }
}
