package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelCreationDto;
import co.simplon.dnd_heroic_battle_api.entities.ArmorType;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import co.simplon.dnd_heroic_battle_api.entities.MonsterType;
import co.simplon.dnd_heroic_battle_api.entities.Size;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonsterModelServiceImplTest {

    @InjectMocks
    private MonsterModelServiceImpl test;

    @Mock
    private MonsterModelRepository repo;

    @Test
    void getOneWithBadId() {
        when(repo.findByMonsterModelCreationDto(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> test.getOne(1L));
    }

    @Test
    void getOne() {
        var modelDto = new MonsterModelCreationDto() {
            @Override
            public Long getModelId() {
                return 1L;
            }

            @Override
            public String getMonsterName() {
                return "name";
            }

            @Override
            public Integer getHitPoints() {
                return 80;
            }

            @Override
            public String getHitPointsRoll() {
                return "5d20";
            }
        };
        when(repo.findByMonsterModelCreationDto(1L)).thenReturn(Optional.of(modelDto));
        assertDoesNotThrow(() -> test.getOne(1L));
        verify(repo, times(1)).findByMonsterModelCreationDto(1L);
    }

    @Test
    void getDetailWithBadId() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> test.getDetail(1L));
    }

    @Test
    void getDetail() {
        var monsterModel = MonsterModel.builder()
                .monsterName("name")
                .hitPoints(10)
                .hitPointsRoll("1d20")
                .strength(10)
                .dexterity(10)
                .constitution(10)
                .intelligence(10)
                .wisdom(10)
                .charisma(10)
                .challengeRating(1.2)
                .xp(500)
                .imageUrl("url")
                .dnd5Native(true)
                .passivePerception(15)
                .monsterType(MonsterType.builder().typeName("type").build())
                .size(Size.builder().sizeName("size").build())
                .armorType(ArmorType.builder().armorType("armor").build())
                .languages(new HashSet<>())
                .conditionsImmunities(new HashSet<>())
                .monsterResistances(new HashSet<>())
                .monsterVulnerabilities(new HashSet<>())
                .monsterImunities(new HashSet<>())
                .specialAbilities(new HashSet<>())
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(monsterModel));
        var actual = assertDoesNotThrow(() -> test.getDetail(1L));
        verify(repo, times(1)).findById(1L);
        assertEquals(monsterModel.getMonsterName(), actual.monsterName());
        assertEquals(monsterModel.getHitPoints(), actual.hitPoints());
        assertEquals(monsterModel.getHitPointsRoll(), actual.hitPointsRoll());
        assertEquals(monsterModel.getStrength(), actual.strength());
        assertEquals(monsterModel.getDexterity(), actual.dexterity());
        assertEquals(monsterModel.getConstitution(), actual.constitution());
        assertEquals(monsterModel.getIntelligence(), actual.intelligence());
        assertEquals(monsterModel.getWisdom(), actual.wisdom());
        assertEquals(monsterModel.getCharisma(), actual.charisma());
        assertEquals(monsterModel.getChallengeRating(), actual.challengeRating());
        assertEquals(monsterModel.getXp(), actual.xp());
        assertEquals(monsterModel.getImageUrl(), actual.imageUrl());
        assertEquals(monsterModel.isDnd5Native(), actual.dnd5Native());
        assertEquals(monsterModel.getPassivePerception(), actual.passivePerception());
        assertEquals(monsterModel.getMonsterType().getTypeName(), actual.monsterType());
        assertEquals(monsterModel.getSize().getSizeName(), actual.size());
        assertEquals(monsterModel.getArmorType().getArmorType(), actual.armorType());
    }

    private static Stream<Arguments> providerForget() {
        return Stream.of(
                Arguments.of("", 10,1,1,1,0,0),
                Arguments.of("name", 50,5,0,0,1,1)
        );
    }

    @ParameterizedTest
    @MethodSource("providerForget")
    void get(String name, Integer limit, Integer page,
             Integer findAll, Integer countTotal,
             Integer findBy, Integer countResult) {
        assertDoesNotThrow(() -> test.get(name,limit,page));
        verify(repo, times(findAll)).findAllPreviewDto(eq(limit), anyInt());
        verify(repo, times(countTotal)).countTotal();
        verify(repo, times(findBy)).findByNamePreviewDto(eq(name),eq(limit),anyInt());
        verify(repo, times(countResult)).countResults(name);
    }


}