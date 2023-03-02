package imdb.challenge.user.controller;

import imdb.challenge.user.model.User;
import imdb.challenge.user.model.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest //sobe o contexto do spring para testes completos
@AutoConfigureJsonTesters // necessario para injetar o jacksonTester
class UserControllerTest {

    @Autowired
    private MockMvc mvc; //simula requisições usando o padrão mvc

    @Autowired
    private JacksonTester<UserDTO> userJson;

    @Test
    @DisplayName("Deve retornar erro 404")
    void changePassword_Cenario1() throws Exception {
        var response = mvc.perform(post("/users"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar a nova senha do usuario, passada na requisição")
    void changePassword_Cenario2() throws Exception {
        var response = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.write(
                                new UserDTO("avner@email.com", "ac333303")
                        ).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}