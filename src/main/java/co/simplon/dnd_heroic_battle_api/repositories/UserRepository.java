package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserNameOrEmail( String name, String email);

    boolean existsByEmailAndUserPassword( String email, String password);

    User findByEmailAndUserPassword( String email, String password);
}
