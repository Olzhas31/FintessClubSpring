package kaz.suleimenov.FitnessClubSpring.controller.errors;

/**
 * Класс для вывода ошибки, если объект не найден.
 */
public class ClientsNotFoundException extends RuntimeException {
    public ClientsNotFoundException(Long id) {
        super("Клиент с id " + id + " не найден.");
    }
}
