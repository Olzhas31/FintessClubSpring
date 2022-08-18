package kaz.suleimenov.FitnessClubSpring.repository;

import kaz.suleimenov.FitnessClubSpring.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
