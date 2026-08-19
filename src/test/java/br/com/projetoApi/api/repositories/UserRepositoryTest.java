package br.com.projetoApi.api.repositories;

import br.com.projetoApi.api.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void shouldFindUserByEmail() {

        // Arrange
        User user = new User(
                null,
                "Marcelo",
                "marcelo@email.com",
                "123456"
        );

        repository.save(user);

        // Act
        Optional<User> response = repository.findByEmail("marcelo@email.com");

        // Assert
        assertTrue(response.isPresent());

        User foundUser = response.get();

        assertAll(
                () -> assertNotNull(foundUser),
                () -> assertNotNull(foundUser.getId()),
                () -> assertEquals("Marcelo", foundUser.getName()),
                () -> assertEquals("marcelo@email.com", foundUser.getEmail()),
                () -> assertEquals("123456", foundUser.getPassword())
        );
    }

    @Test
    void shouldReturnEmptyWhenEmailDoesNotExist() {

        // Act
        Optional<User> response =
                repository.findByEmail("naoexiste@email.com");

        // Assert
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}