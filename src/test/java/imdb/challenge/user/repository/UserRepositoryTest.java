package imdb.challenge.user.repository;

import imdb.challenge.user.model.User;
import imdb.challenge.user.model.UserDTO;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // anotação obrogatoria para se testar a camada de dados
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // dispensar a autoconfig do spring
@ActiveProfiles("test") //selecionar o profile ativo para o teste
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager manager; // usado para criar entidades para o teste, via metodos privados

    @Test
    @DisplayName("Esperado retorno OK, para o email passado")
    void findByUsername_Cenario1() {
        User userTest = createUser("avner@email.com", "a165854973*");
        UserDetails user = repository.findByUsername("avner@email.com");
        Assertions.assertThat(user).isEqualTo(userTest);
    }

    @Test
    @DisplayName("Esperado retorno nulo para o email passado")
    void findByUsername_Cenario2() {
        User userTest = createUser("avner@email.com", "a165854973*");
        UserDetails user = repository.findByUsername("prior@email.com");
        Assertions.assertThat(user).isNotEqualTo(userTest);
    }

    // metodo privado para a criação de uma entidade fake
    private User createUser(String email, String password) {
        User user =  new User(email, password);
        manager.persist(user);
        return user;
    }
}