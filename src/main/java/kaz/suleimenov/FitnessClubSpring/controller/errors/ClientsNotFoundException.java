package kaz.suleimenov.FitnessClubSpring.controller.errors;

public class ClientsNotFoundException extends RuntimeException {
    public ClientsNotFoundException(Long id) {
        super("Клиент с id " + id + " не найден.");
    }
}
