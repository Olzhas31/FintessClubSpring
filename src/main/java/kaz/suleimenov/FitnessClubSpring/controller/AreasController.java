package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.AreasNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Areas;
import kaz.suleimenov.FitnessClubSpring.repository.AreasRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreasController {
    
    private final AreasRepository areasRepository;

    public AreasController(AreasRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    @GetMapping("/areas")
    public List<Areas> all() {
        return areasRepository.findAll();
    }

    @PostMapping("/areas")
    public Areas newArea(@RequestBody Areas newArea) {
        return areasRepository.save(newArea);
    }

    @GetMapping("/areas/{id}")
    public Areas one(@PathVariable Long id) {
        return areasRepository.findById(id)
                .orElseThrow(() -> new AreasNotFoundException(id));
    }

    @PutMapping("/areas/{id}")
    public Areas replaceClient(@RequestBody Areas newArea, @PathVariable Long id) {
        return areasRepository.findById(id)
                .map(area -> {
                    area.setName(newArea.getName());
                    return areasRepository.save(area);
                })
                .orElseGet(() -> {
                    newArea.setId(id);
                    return areasRepository.save(newArea);
                });
    }

    @DeleteMapping("/areas/{id}")
    public void deleteClient(@PathVariable Long id) {
        areasRepository.deleteById(id);
    }
}
