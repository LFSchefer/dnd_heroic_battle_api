package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserNameOrEmailIgnoreCase(String name, String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
