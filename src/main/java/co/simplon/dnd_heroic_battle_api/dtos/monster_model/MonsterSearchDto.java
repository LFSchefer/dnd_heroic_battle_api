package co.simplon.dnd_heroic_battle_api.dtos.monster_model;

import java.util.List;

public record MonsterSearchDto(List<MonsterPreviewDto> result, int currentPage, int totalPages) {
}
