package imdb.challenge.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Imdb Challenge")
                        .description("Versão inicial do projeto contendo recursos de login, cadastros de usuarios e listagem de filmes")
                        .contact(new Contact()
                                .name("Avner Caleb")
                                .email("avnercaleb@email.com"))
                        .version("0.0.1")
                        .license(new License()
                                .name("GPLv3")
                                .url("https://www.gnu.org/licenses/quick-guide-gplv3.pt-br.html")));


    }
}
