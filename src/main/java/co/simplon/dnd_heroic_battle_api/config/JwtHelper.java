package co.simplon.dnd_heroic_battle_api.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public final class JwtHelper {

    public static SecurityContext securityContext() {
        return SecurityContextHolder.getContext();
    }

    public static Authentication authentication() {
        return securityContext().getAuthentication();
    }

    public static Jwt getPrincipal() {
        return (Jwt) authentication().getPrincipal();
    }

    public static Long getSubject() {
        return Long.valueOf(getPrincipal().getClaimAsString("sub"));
    }


}
