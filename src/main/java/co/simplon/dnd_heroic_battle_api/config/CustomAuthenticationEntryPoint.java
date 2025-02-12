package co.simplon.dnd_heroic_battle_api.config;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();

    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
	    this.delegate.commence(request, response, authException);

		if (authException.getCause() instanceof JwtValidationException validationException) {
            var problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
            problemDetail.setType(URI.create("/users/token-renewal"));
            problemDetail.setProperty("errors", validationException.getErrors());
            problemDetail.setTitle("Invalid Token");
            mapper.writeValue(response.getWriter(), problemDetail);
		}
		
	}

}
