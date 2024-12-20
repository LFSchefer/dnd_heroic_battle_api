package co.simplon.dnd_heroic_battle_api.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;

public class JwtProvider {

	private final Algorithm algorithm;
	private final int expire;
	private final String issuer;
	private final int refreshExpire;

	public JwtProvider(Algorithm algorithm, int expire, String issuer, int refreshExpire) {
		this.algorithm = algorithm;
		this.expire = expire;
		this.issuer = issuer;
		this.refreshExpire = refreshExpire;
	}
	
	public String create(String subject, UUID uuid) {
		Instant instant = Instant.now();
		Builder builder = JWT.create()
				.withIssuedAt(instant)
				.withIssuer(issuer)
				.withClaim("ref", uuid.toString())
				.withSubject(subject);
		if (expire > -1) {
			builder.withExpiresAt(instant.plus(expire, ChronoUnit.MINUTES));
		}
		return builder.sign(algorithm);
	}
	
	public String createRefresh(UUID uuid) {
		Instant instant = Instant.now();
		Builder builder = JWT.create()
				.withIssuedAt(instant)
				.withClaim("ref", uuid.toString());
		if (refreshExpire > -1) {
			builder.withExpiresAt(instant.plus(refreshExpire, ChronoUnit.MINUTES));
		}
		return builder.sign(algorithm);
	}
	
	public Tokens generate(String subject) {
		UUID uuid = UUID.randomUUID();
		Long exp = Instant.now().plus(expire, ChronoUnit.MINUTES).toEpochMilli();
		return new Tokens(create(subject, uuid), createRefresh(uuid), exp);
	}
}
