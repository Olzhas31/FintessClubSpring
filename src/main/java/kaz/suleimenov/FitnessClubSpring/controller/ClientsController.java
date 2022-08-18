package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.ClientsNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Clients;
import kaz.suleimenov.FitnessClubSpring.repository.ClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientsController {
    
    private final ClientsRepository clientsRepository;

    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping("/clients")
    public List<Clients> all() {
        return clientsRepository.findAll();
    }

    @PostMapping("/clients")
    public Clients newClient(@RequestBody Clients newClient) {
        return clientsRepository.save(newClient);
    }

    @GetMapping("/clients/{id}")
    public Clients one(@PathVariable Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(() -> new ClientsNotFoundException(id));
    }

    @PutMapping("/clients/{id}")
    public Clients replaceClient(@RequestBody Clients newClient, @PathVariable Long id) {
        return clientsRepository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setPassport(newClient.getPassport());
                    client.setPhone(newClient.getPhone());
                    return clientsRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientsRepository.save(newClient);
                });
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientsRepository.deleteById(id);
    }
}
