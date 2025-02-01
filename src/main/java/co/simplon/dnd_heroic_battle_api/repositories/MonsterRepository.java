package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.dnd_heroic_battle_api.entities.Monster;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    String CREATE = """ 
            INSERT INTO monsters (current_hit_points, monster_name, battle_id, monster_id)
            VALUES (:currentHitPoint,:name, :battleId, :monsterId);
            """;

    @Modifying
    @Query(value = CREATE, nativeQuery = true)
    void create(@Param("name") String name, @Param("currentHitPoint") int currentHitPoint, @Param("monsterId") Long monsterId, @Param("battleId") Long battleId);

}
