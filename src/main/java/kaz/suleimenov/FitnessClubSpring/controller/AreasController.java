package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.AreasNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Areas;
import kaz.suleimenov.FitnessClubSpring.repository.AreasRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллера для залов, где задаются HTTP запросы.
 */
@RestController
public class AreasController {
    
    private final AreasRepository areasRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param areasRepository - репозиторий залов
     */
    public AreasController(AreasRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    /**
     * GET запрос
     * @return все залы
     */
    @GetMapping("/areas")
    List<Areas> all() {
        return areasRepository.findAll();
    }

    /**
     * POST запрос
     * @param newArea - новый зал
     * @return новый зал
     */
    @PostMapping("/areas")
    Areas newArea(@RequestBody Areas newArea) {
        return areasRepository.save(newArea);
    }

    /**
     * GET запрос определенного зала
     * @param id - id зала
     * @return зал по id
     */
    @GetMapping("/areas/{id}")
    Areas one(@PathVariable Long id) {

        return areasRepository.findById(id)
                .orElseThrow(() -> new AreasNotFoundException(id));
    }

    /**
     * PUT запрос на изменение зала
     * @param newArea - новый зал
     * @param id - id зала
     * @return измененный зал
     */
    @PutMapping("/areas/{id}")
    Areas replaceClient(@RequestBody Areas newArea, @PathVariable Long id) {

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

    /**
     * DELETE запрос
     * @param id - id зала
     */
    @DeleteMapping("/areas/{id}")
    void deleteClient(@PathVariable Long id) {
        areasRepository.deleteById(id);
    }
}
