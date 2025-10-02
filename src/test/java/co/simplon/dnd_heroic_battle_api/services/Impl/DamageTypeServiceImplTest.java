package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DamageTypeServiceImplTest {

    @InjectMocks
    private DamageTypeServiceImpl test;

    @Mock
    private DamageTypeRepository repo;

    @Test
    void getAll() {
        DamageType damageType = DamageType.builder()
                .damageTypeId(156L)
                .damageTypeName("fire")
                .description("description")
                .build();
        when(repo.findAll()).thenReturn(List.of(damageType));
        assertDoesNotThrow(() -> test.getAll());
    }

}