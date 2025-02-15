package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MonstersServiceImplTest {

    @InjectMocks
    private MonstersServiceImpl test;

    @Mock
    private MonsterRepository repo;

    @Test
    void create() {
        var input = new MonsterCreateDto("name",42,42,1L,1L);
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).create("name",42,42,1L,1L);
    }

}