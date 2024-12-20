package co.simplon.dnd_heroic_battle_api.dtos.user;

import java.time.Instant;

public record UserView(String userName, String email, Tokens tokens) {
}
