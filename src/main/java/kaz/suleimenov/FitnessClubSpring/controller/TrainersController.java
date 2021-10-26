package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.TrainersNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Trainers;
import org.springframework.web.bind.annotation.*;
import kaz.suleimenov.FitnessClubSpring.repository.TrainersRepository;

import java.util.List;

/**
 * Класс контроллера для тренеров, где задаются HTTP запросы.
 */
@RestController
public class TrainersController {

    private final TrainersRepository trainersRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param trainersRepository - репозиторий тренеров
     */
    public TrainersController(TrainersRepository trainersRepository) {
        this.trainersRepository = trainersRepository;
    }

    /**
     * GET запрос
     * @return все тренеры
     */
    @GetMapping("/trainers")
    List<Trainers> all() {
        return trainersRepository.findAll();
    }

    /**
     * POST запрос
     * @param newTrainer - новый тренер
     * @return новый тренер
     */
    @PostMapping("/trainers")
    Trainers newTrainer(@RequestBody Trainers newTrainer) {
        return trainersRepository.save(newTrainer);
    }

    /**
     * GET запрос определенного тренера
     * @param id - id тренера
     * @return тренер по id
     */
    @GetMapping("/trainers/{id}")
    Trainers one(@PathVariable Long id) {

        return trainersRepository.findById(id)
                .orElseThrow(() -> new TrainersNotFoundException(id));
    }

    /**
     * PUT запрос на изменение тренера
     * @param newTrainer - новый тренер
     * @param id - id тренера
     * @return измененный тренер
     */
    @PutMapping("/trainers/{id}")
    Trainers replaceTrainer(@RequestBody Trainers newTrainer, @PathVariable Long id) {

        return trainersRepository.findById(id)
                .map(trainer -> {
                    trainer.setName(newTrainer.getName());
                    trainer.setPassport(newTrainer.getPassport());
                    trainer.setPhone(newTrainer.getPhone());
                    trainer.setAddress(newTrainer.getAddress());
                    return trainersRepository.save(trainer);
                })
                .orElseGet(() -> {
                    newTrainer.setId(id);
                    return trainersRepository.save(newTrainer);
                });
    }

    /**
     * DELETE запрос
     * @param id - id тренера
     */
    @DeleteMapping("/trainers/{id}")
    void deleteTrainer(@PathVariable Long id) {
        trainersRepository.deleteById(id);
    }

    /**
     * Поиск по частям в ФИО (в тренере не используется)
     * @param name - ФИО
     * @return - найденный тренер
     */
    @GetMapping("/trainers/find/{name}")
    public List<Trainers> findAll(@PathVariable String name) {
        return trainersRepository.findByNameContaining(name);
    }

}

