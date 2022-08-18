package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.SubscriptionsNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.*;
import kaz.suleimenov.FitnessClubSpring.repository.ActivitiesRepository;
import kaz.suleimenov.FitnessClubSpring.repository.ClientsRepository;
import kaz.suleimenov.FitnessClubSpring.repository.SubscriptionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubscriptionsController {

    private final SubscriptionsRepository subscriptionsRepository;
    private final ActivitiesRepository activitiesRepository;
    private final ClientsRepository clientsRepository;

    public SubscriptionsController(SubscriptionsRepository subscriptionsRepository, ActivitiesRepository activitiesRepository, ClientsRepository clientsRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.activitiesRepository = activitiesRepository;
        this.clientsRepository = clientsRepository;
    }

    @GetMapping("/subscriptions")
    public List<Subscriptions> all() {
        return subscriptionsRepository.findAll();
    }

    @PostMapping("/subscriptions")
    public Subscriptions newSubscription(@RequestBody Subscriptions newSubscription) {
        return subscriptionsRepository.save(newSubscription);
    }

    @GetMapping("/subscriptions/{id}")
    public Subscriptions one(@PathVariable Long id) {
        return subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
    }

    @PutMapping("/subscriptions/{id}")
    public Subscriptions replaceClient(@RequestBody Subscriptions newSubscription, @PathVariable Long id) {
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

    @DeleteMapping("/subscriptions/{id}")
    public void deleteClient(@PathVariable Long id) {
        subscriptionsRepository.deleteById(id);
    }
}
