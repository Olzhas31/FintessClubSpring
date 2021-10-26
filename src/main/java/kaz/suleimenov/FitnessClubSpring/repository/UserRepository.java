package kaz.suleimenov.FitnessClubSpring.repository;

import kaz.suleimenov.FitnessClubSpring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий аккаунтов.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
