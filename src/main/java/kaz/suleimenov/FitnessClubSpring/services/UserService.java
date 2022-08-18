package kaz.suleimenov.FitnessClubSpring.services;

import kaz.suleimenov.FitnessClubSpring.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User create(User user);

    User getById(Long id);

    User update(User user);

    void deleteById(Long id);
}
