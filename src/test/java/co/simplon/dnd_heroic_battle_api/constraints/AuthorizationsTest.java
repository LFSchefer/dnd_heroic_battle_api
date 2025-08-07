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

//    @Test
//    void shouldBeAuthorized() throws Exception {
//        perform("POST", "/api/users", null, "{\"userName\":\"test user\", \"email\":\"test@mail.com\", \"password\": \"Azerty!123\"}")
//                .andExpect(status().isCreated());
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/constraints/authorizations/authorized.csv", numLinesToSkip = 1, delimiter = '¤')
    void shouldBeAuthorized(String method, String path, String token, String json, int status) throws Exception {
        perform(method, path, token, json)
                .andExpect(status().is(status));
    }

//    @Test
//    void shouldNotAuthorize() throws Exception {
//        perform("POST", "/api/users", validToken, "{\"userName\":\"test user\", \"email\":\"test@mail.com\", \"password\": \"Azerty!123\"}")
//                .andExpect(status().is4xxClientError());
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/constraints/authorizations/not-authorized.csv", numLinesToSkip = 1, delimiter = '¤')
    void shouldNotAuthorize(String method, String path, String token, String json, int status) throws Exception {
        perform(method, path, Objects.equals(token, "null") ? null : expiredToken, json)
                .andExpect(status().is4xxClientError());
    }
}
