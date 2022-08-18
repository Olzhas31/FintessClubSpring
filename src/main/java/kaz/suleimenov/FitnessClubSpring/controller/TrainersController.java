package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.TrainersNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Trainers;
import org.springframework.web.bind.annotation.*;
import kaz.suleimenov.FitnessClubSpring.repository.TrainersRepository;

import java.util.List;

@RestController
public class TrainersController {

    private final TrainersRepository trainersRepository;

    public TrainersController(TrainersRepository trainersRepository) {
        this.trainersRepository = trainersRepository;
    }

    @GetMapping("/trainers")
    public List<Trainers> all() {
        return trainersRepository.findAll();
    }

    @PostMapping("/trainers")
    public Trainers newTrainer(@RequestBody Trainers newTrainer) {
        return trainersRepository.save(newTrainer);
    }

    @GetMapping("/trainers/{id}")
    public Trainers one(@PathVariable Long id) {
        return trainersRepository.findById(id)
                .orElseThrow(() -> new TrainersNotFoundException(id));
    }

    @PutMapping("/trainers/{id}")
    public Trainers replaceTrainer(@RequestBody Trainers newTrainer, @PathVariable Long id) {
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

    @DeleteMapping("/trainers/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainersRepository.deleteById(id);
    }

    @GetMapping("/trainers/find/{name}")
    public List<Trainers> findAll(@PathVariable String name) {
        return trainersRepository.findByNameContaining(name);
    }

}

