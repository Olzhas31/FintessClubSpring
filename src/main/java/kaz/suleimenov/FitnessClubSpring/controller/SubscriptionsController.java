package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.SubscriptionsNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.*;
import kaz.suleimenov.FitnessClubSpring.repository.ActivitiesRepository;
import kaz.suleimenov.FitnessClubSpring.repository.ClientsRepository;
import kaz.suleimenov.FitnessClubSpring.repository.SubscriptionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Класс контроллера для абонементов, где задаются HTTP запросы.
 */
@RestController
public class SubscriptionsController {

    private final SubscriptionsRepository subscriptionsRepository;
    private final ActivitiesRepository activitiesRepository;
    private final ClientsRepository clientsRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param subscriptionsRepository - репозиторий абонементов
     * @param activitiesRepository - репозиторий занятий
     * @param clientsRepository - репозиторий клиентов
     */
    public SubscriptionsController(SubscriptionsRepository subscriptionsRepository, ActivitiesRepository activitiesRepository, ClientsRepository clientsRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.activitiesRepository = activitiesRepository;
        this.clientsRepository = clientsRepository;
    }

    /**
     * GET запрос
     * @return все абонементы
     */
    @GetMapping("/subscriptions")
    List<Subscriptions> all() {
        return subscriptionsRepository.findAll();
    }

    /**
     * POST запрос
     * @param newSubscription - новый абонемент
     * @return новый абонемент
     */
    @PostMapping("/subscriptions")
    Subscriptions newSubscription(@RequestBody Subscriptions newSubscription) {
        return subscriptionsRepository.save(newSubscription);
    }

    /**
     * GET запрос определенного абонемента
     * @param id - id абонемента
     * @return абонемент по id
     */
    @GetMapping("/subscriptions/{id}")
    Subscriptions one(@PathVariable Long id) {

        return subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
    }

    /**
     * PUT запрос на изменение абонемента
     * @param newSubscription - новый абонемент
     * @param id - id абонемента
     * @return измененный абонемент
     */
    @PutMapping("/subscriptions/{id}")
    Subscriptions replaceClient(@RequestBody Subscriptions newSubscription, @PathVariable Long id) {

        Optional<Activities> activity = activitiesRepository.findById(newSubscription.getActivity().getId());
        Optional<Clients> client = clientsRepository.findById(newSubscription.getClient().getId());

        return subscriptionsRepository.findById(id)
                .map(subscription -> {
                    subscription.setClient(client.get());
                    subscription.setActivity(activity.get());
                    subscription.setDate(newSubscription.getDate());
                    subscription.setPrice(newSubscription.getPrice());
                    return subscriptionsRepository.save(subscription);
                })
                .orElseGet(() -> {
                    newSubscription.setId(id);
                    return subscriptionsRepository.save(newSubscription);
                });
    }

    /**
     * DELETE запрос
     * @param id - id абонемента
     */
    @DeleteMapping("/subscriptions/{id}")
    void deleteClient(@PathVariable Long id) {
        subscriptionsRepository.deleteById(id);
    }
}
