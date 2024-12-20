package co.simplon.dnd_heroic_battle_api.dtos.user;

public record Tokens(String token, String refreshToken, Long expiration) {
}
