package co.simplon.dnd_heroic_battle_api.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

public class JwtUtils {
	
	private final JwtDecoder decoder;
	
	public JwtUtils(JwtDecoder decoder) {
		this.decoder = decoder;
	}
	
	public String getSubject(String token) {
		String[] bearer = token.split(" ");
		Jwt dec = decoder.decode(bearer[1]);
		String subject = dec.getClaimAsString("sub");
		return subject;
	}

}
