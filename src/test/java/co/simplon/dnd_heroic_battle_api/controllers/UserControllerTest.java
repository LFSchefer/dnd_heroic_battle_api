package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.MvcTestHelper;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends MvcTestHelper {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreate() throws Exception {
        String json = "{\"userName\":\"John Doe\", \"email\":\"john@doe.com\", \"password\": \"Azerty!123\"}";
        perform("POST", "/api/users", null, json)
                .andExpect(status().isCreated());
        assertTrue(userRepository.existsByEmail("john@doe.com"));
    }

    @Test
    public void shouldThrowCreate() throws Exception {
        String json = "{\"userName\":\"Hutkelm\", \"email\":\"hutkelm@hotmail.fr\", \"password\": \"Azerty!123\"}";
        perform("POST", "/api/users", null, json)
                .andExpect(status().isBadRequest());
        assertTrue(userRepository.existsByEmail("hutkelm@hotmail.fr"));
    }

    @Test
    public void shouldSignIn() throws Exception {
        String json = "{\"email\":\"hutkelm@hotmail.fr\", \"password\": \"Azerty!123\"}";
        perform("POST", "/api/users/sign-in", null, json)
                .andExpect(status().isOk());
        assertTrue(userRepository.existsByEmail("hutkelm@hotmail.fr"));
    }

    @Test
    public void shouldThrowSignIn() throws Exception {
        String json = "{\"email\":\"no@mail.mr\", \"password\": \"Azerty!123\"}";
        perform("POST", "/api/users/sign-in", null, json)
                .andExpect(status().isBadRequest());
        assertFalse(userRepository.existsByEmail("no@mail.mr"));
    }


}