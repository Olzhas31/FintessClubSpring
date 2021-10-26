package kaz.suleimenov.FitnessClubSpring.repository;

import kaz.suleimenov.FitnessClubSpring.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий абонементов.
 */
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {
}
