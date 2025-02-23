package br.com.colli.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/*
O Quarkus precisa que as variáveis estejam disponíveis antes de
carregar as configurações do application.properties.
Podemos garantir isso carregando-as logo no início da aplicação.
 */
@ApplicationScoped
public class EnvLoader {

    @PostConstruct
    void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // Lê da raiz do projeto
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
