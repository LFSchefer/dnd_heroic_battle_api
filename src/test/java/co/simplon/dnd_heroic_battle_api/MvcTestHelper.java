package co.simplon.dnd_heroic_battle_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

@SpringBootTest(classes = DndHeroicBattleApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource({"/test/application-test.properties"})
public class MvcTestHelper {

    @Value("${dnd_heroic_battle.tests.expired-token}")
    public String expiredToken;
    @Value("${dnd_heroic_battle.tests.valid-token}")
    public String validToken;
    @Autowired
    MockMvc mvc;

    protected final ResultActions perform(String method, String path, String token, String json) throws Exception {
        var builder = requestBuilder(method, path, token, json);
        return mvc.perform(builder);
    }

    protected final MockHttpServletRequestBuilder requestBuilder(String method, String path, String token, String json) {
        var builder = request(HttpMethod.valueOf(method), path);
        if (!Objects.equals(token, null)) {
            builder.header("Authorization", token);
        }
        if (json != null) {
            builder.contentType(MediaType.APPLICATION_JSON)
                    .content(json);
        }
        return builder;
    }

}
