package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserNameOrEmail( String name, String email);

    boolean existsByEmail( String email);

    Optional<User> findByEmail(String email);
}
