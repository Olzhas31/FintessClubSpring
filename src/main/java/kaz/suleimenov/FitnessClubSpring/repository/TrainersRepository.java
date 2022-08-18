package kaz.suleimenov.FitnessClubSpring.repository;

import kaz.suleimenov.FitnessClubSpring.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainersRepository extends JpaRepository<Trainers, Long> {
    List<Trainers> findByNameContaining(String name);
}
