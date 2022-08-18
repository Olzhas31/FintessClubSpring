package kaz.suleimenov.FitnessClubSpring.controller.errors;

public class TrainersNotFoundException extends RuntimeException {
    public TrainersNotFoundException(Long id) {
        super("Тренер с id " + id + " не найден.");
    }
}
