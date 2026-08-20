package br.com.projetoapi.api.config;

import br.com.projetoapi.api.domain.User;
import br.com.projetoapi.api.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository repository;

    public LocalConfig(UserRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void startDB() {
        User u1 = new User(null, "Marcelo", "marcelo@mail.com", "123");
        User u2 = new User(null, "Luiz", "luiz@mail.com", "123");

        repository.saveAll(List.of(u1, u2));
    }
}
