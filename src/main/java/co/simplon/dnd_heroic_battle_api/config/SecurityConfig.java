package co.simplon.dnd_heroic_battle_api.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class SecurityConfig {
	
	@Value("${dnd_heroic_battle.cors}")
	private String origins;
	@Value("${dnd_heroic_battle.bcrypt.cost}")
	private int cost;
	@Value("${dnd_heroic_battle.jwt.secret}")
	private String secret;
	@Value("${dnd_heroic_battle.jwt.expire}")
	private int expire;
	@Value("${dnd_heroic_battle.jwt.refresh.expire}")
	private int refreshExpire;
	@Value("${dnd_heroic_battle.jwt.issuer}")
	private String issuer;

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE").allowedOrigins(origins);
			}
		};
	}
	
	// Authorization server configuration
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2B,cost);
	}
	
	@Bean
	JwtProvider jwtProvider() {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return new JwtProvider(algorithm, expire, issuer , refreshExpire);
	}

	// Resources server configuration
	
	@Bean
	JwtDecoder jwtDecoder() {
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA256");
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey)
				.macAlgorithm(MacAlgorithm.HS256).build();
		OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);
		decoder.setJwtValidator(validator);
		return decoder;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationEntryPoint entryPoint) throws Exception {
		http.cors(Customizer.withDefaults())
		.csrf( csrf -> csrf.disable())
		.authorizeHttpRequests( request -> request
			.requestMatchers(HttpMethod.GET, "api/v1/import-data").permitAll())
		.authorizeHttpRequests( request -> request
			.requestMatchers(HttpMethod.POST, "api/v1/users", "api/v1/users/sign-in").anonymous()
			.requestMatchers(HttpMethod.POST, "api/v1/users/token-renewal").anonymous())
		.authorizeHttpRequests( request -> request
				.anyRequest().authenticated())
		.oauth2ResourceServer(oauth -> 
			oauth.jwt(Customizer.withDefaults())
			.authenticationEntryPoint(entryPoint));
		return http.build();
	}
	
	

}
