package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelCreationPro;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelPreviewPro;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterModelRepository extends JpaRepository<MonsterModel, Long> {

    String FIND_ALL = """
            SELECT model_id, monster_name, challenge_rating FROM monster_models
            LIMIT :limit
            OFFSET :offset
            """;

    String FIND_ALL_BY_NAME = """
            SELECT model_id, monster_name, challenge_rating FROM monster_models
            WHERE UPPER(monster_name) LIKE UPPER(CONCAT('%',:name,'%'))
            LIMIT :limit
            OFFSET :offset
            """;

    String COUNT_RESULTS = """
            SELECT count(*) FROM monster_models
            WHERE UPPER(monster_name) LIKE UPPER(CONCAT('%',:name,'%'))
            """;

    String COUNT_TOTAL = """
            SELECT count(*) from monster_models
            """;

    String FIND_BY_ID = """
            SELECT model_id, monster_name, hit_points, hit_points_roll FROM monster_models
            WHERE model_id = :id;
            """;

    @NativeQuery(value = FIND_ALL)
    List<MonsterModelPreviewPro> findAllPreviewDto(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @NativeQuery(value = FIND_ALL_BY_NAME)
    List<MonsterModelPreviewPro> findByNamePreviewDto(@Param("name") String name, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @NativeQuery(value = COUNT_RESULTS)
    Integer countResults(@Param("name") String name);

    @NativeQuery(value = COUNT_TOTAL)
    Integer countTotal();

    @NativeQuery(value = FIND_BY_ID)
    Optional<MonsterModelCreationPro> findByMonsterModelCreationDto(long id);
}
