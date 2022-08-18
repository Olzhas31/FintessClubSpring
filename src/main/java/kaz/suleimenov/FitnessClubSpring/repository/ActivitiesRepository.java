package kaz.suleimenov.FitnessClubSpring.repository;

import kaz.suleimenov.FitnessClubSpring.entity.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
}
