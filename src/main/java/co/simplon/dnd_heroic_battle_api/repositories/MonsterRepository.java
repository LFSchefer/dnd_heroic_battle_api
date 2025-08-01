package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativePro;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    String CREATE = """ 
            INSERT INTO monsters (current_hit_points, max_hit_points, monster_name, battle_id, model_id)
            VALUES (:currentHitPoint, :maxHitPoints ,:name, :battleId, :modelId);
            """;
    String FIND_ALL_BY_BATTLE = """
            SELECT * FROM monsters m
            WHERE battle_id = :battleId;
            """;
    String GET_ALL_INITIATIVE_BY_BATTLE = """
            SELECT m.monster_id , m.monster_name, m.initiative, m2.dexterity FROM monsters m
            INNER JOIN monster_models m2 ON m.model_id = m2.model_id
            WHERE m.battle_id = :battleId;
            """;

    @Modifying
    @NativeQuery(value = CREATE)
    void create(@Param("name") String name,
                @Param("currentHitPoint") int currentHitPoint,
                @Param("maxHitPoints") int maxHitPoints,
                @Param("modelId") Long modelId,
                @Param("battleId") Long battleId);

    @NativeQuery(value = FIND_ALL_BY_BATTLE)
    Set<Monster> getAllByBattle(Long battleId);

    @NativeQuery(value = GET_ALL_INITIATIVE_BY_BATTLE)
    Set<MonsterInitiativePro> getAllInitiativeByBattle(@Param("battleId") Long battleId);
}