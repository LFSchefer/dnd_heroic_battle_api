package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BattleMonstersRepository extends JpaRepository<BattleMonster, Long> {

    String CREATE_BM = """ 
            INSERT INTO battle_monsters (current_hit_points, battle_monster_name, battle_id, monster_id) 
            VALUES (:currentHitPoint,:name, :battleId, :monsterId);
            """;

    @Modifying
    @Query(value = CREATE_BM, nativeQuery = true)
    void create(@Param("name") String name, @Param("currentHitPoint") int currentHitPoint, @Param("monsterId") Long monsterId, @Param("battleId") Long battleId);

}
