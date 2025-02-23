package br.com.colli.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DotenvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory(System.getProperty("user.dir")) // Confirma que está lendo da raiz
            .ignoreIfMissing() // Não quebra caso o arquivo esteja ausente
            .load();

    public String get(String key) {
        return dotenv.get(key);
    }

    static {
        dotenv.entries().forEach(entry -> {
            System.out.println("Carregado: " + entry.getKey() + " = " + entry.getValue());
        });
    }
}
