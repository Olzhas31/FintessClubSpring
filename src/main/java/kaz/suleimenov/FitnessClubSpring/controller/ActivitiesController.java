package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.ActivitiesNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Activities;
import kaz.suleimenov.FitnessClubSpring.entity.Areas;
import kaz.suleimenov.FitnessClubSpring.entity.Trainers;
import kaz.suleimenov.FitnessClubSpring.repository.ActivitiesRepository;
import kaz.suleimenov.FitnessClubSpring.repository.AreasRepository;
import kaz.suleimenov.FitnessClubSpring.repository.TrainersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ActivitiesController {
    
    private final ActivitiesRepository activitiesRepository;
    private final TrainersRepository trainersRepository;
    private final AreasRepository areasRepository;

    public ActivitiesController(ActivitiesRepository activitiesRepository, TrainersRepository trainersRepository,
                                AreasRepository areasRepository) {
        this.activitiesRepository = activitiesRepository;
        this.trainersRepository = trainersRepository;
        this.areasRepository = areasRepository;
    }

    @GetMapping("/activities")
    public List<Activities> all() {
        return activitiesRepository.findAll();
    }

    @PostMapping("/activities")
    public ResponseEntity<?> newActivity(@RequestBody Activities newActivity) {
        activitiesRepository.save(newActivity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/activities/{id}")
    public Activities one(@PathVariable Long id) {
        return activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));
    }

    @PutMapping("/activities/{id}")
    public Activities replaceClient(@RequestBody Activities newActivity, @PathVariable Long id) {
        Optional<Trainers> trainer = trainersRepository.findById(newActivity.getTrainer().getId());
        Optional<Areas> area = areasRepository.findById(newActivity.getArea().getId());

        return activitiesRepository.findById(id)
                .map(activity -> {
                    activity.setName(newActivity.getName());
                    activity.setTrainer(trainer.get());
                    activity.setArea(area.get());
                    return activitiesRepository.save(activity);
                })
                .orElseGet(() -> {
                    newActivity.setId(id);
                    return activitiesRepository.save(newActivity);
                });
    }

    @DeleteMapping("/activities/{id}")
    public void deleteClient(@PathVariable Long id) {
        activitiesRepository.deleteById(id);
    }
}
