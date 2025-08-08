package co.simplon.dnd_heroic_battle_api.constraints;

import co.simplon.dnd_heroic_battle_api.MvcTestHelper;
import co.simplon.dnd_heroic_battle_api.controllers.UserController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorizationsTest extends MvcTestHelper {

    @MockitoBean
    private UserController controller;

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/constraints/authorizations/authorized.csv", numLinesToSkip = 1, delimiter = '¤')
    void shouldBeAuthorized(String method, String path, String tokenName, String json, int status) throws Exception {
        String token = null;
        if (Objects.equals("validToken", tokenName)) {
            token = validToken;
        } else if (Objects.equals("expiredToken", tokenName)) {
            token = expiredToken;
        }
        perform(method, path, token, json)
                .andExpect(status().is(status));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/constraints/authorizations/not-authorized.csv", numLinesToSkip = 1, delimiter = '¤')
    void shouldNotAuthorize(String method, String path, String tokenName, String json, int status) throws Exception {
        String token = null;
        if (Objects.equals("validToken", tokenName)) {
            token = validToken;
        } else if (Objects.equals("expiredToken", tokenName)) {
            token = expiredToken;
        }
        perform(method, path, token, json)
                .andExpect(status().is(status));
    }
}
