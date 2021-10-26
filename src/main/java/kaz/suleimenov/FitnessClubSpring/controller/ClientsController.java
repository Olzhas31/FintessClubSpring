package kaz.suleimenov.FitnessClubSpring.controller;

import kaz.suleimenov.FitnessClubSpring.controller.errors.ClientsNotFoundException;
import kaz.suleimenov.FitnessClubSpring.entity.Clients;
import kaz.suleimenov.FitnessClubSpring.repository.ClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллера для клиентов, где задаются HTTP запросы.
 */
@RestController
public class ClientsController {
    
    private final ClientsRepository clientsRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param clientsRepository - репозиторий клиентов
     */
    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    /**
     * GET запрос
     * @return все клиенты
     */
    @GetMapping("/clients")
    List<Clients> all() {
        return clientsRepository.findAll();
    }

    /**
     * POST запрос
     * @param newClient - новый клиент
     * @return новый клиент
     */
    @PostMapping("/clients")
    Clients newClient(@RequestBody Clients newClient) {
        return clientsRepository.save(newClient);
    }

    /**
     * GET запрос определенного клиента
     * @param id - id клиента
     * @return клиент по id
     */
    @GetMapping("/clients/{id}")
    Clients one(@PathVariable Long id) {

        return clientsRepository.findById(id)
                .orElseThrow(() -> new ClientsNotFoundException(id));
    }


    /**
     * PUT запрос на изменение клиента
     * @param newClient - новый клиент
     * @param id - id клиента
     * @return измененный клиент
     */
    @PutMapping("/clients/{id}")
    Clients replaceClient(@RequestBody Clients newClient, @PathVariable Long id) {

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

    /**
     * DELETE запрос
     * @param id - id клиента
     */
    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        clientsRepository.deleteById(id);
    }
}
