package kaz.suleimenov.FitnessClubSpring.services;

import kaz.suleimenov.FitnessClubSpring.controller.errors.UserAlreadyExistsException;
import kaz.suleimenov.FitnessClubSpring.controller.errors.UserNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.User;
import kaz.suleimenov.FitnessClubSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        if (userRepository.existsUserByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException(user.getLogin());
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User update(User user) {
        return userRepository.findById(user.getId())
                .map(area -> {
                    area.setLogin(user.getLogin());
                    area.setPassword(user.getPassword());
                    return userRepository.saveAndFlush(area);
                })
                .orElseGet(() -> {
                    return userRepository.saveAndFlush(user);
                });
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
