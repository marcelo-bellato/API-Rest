package br.com.projetoApi.api.resources;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class UserResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Test
    void shouldCreateUserSuccessfully() throws Exception {

        // Arrange
        String email = "integration@email.com";

        String requestBody = """
                {
                    "name": "Marcelo",
                    "email": "%s",
                    "password": "123456"
                }
                """.formatted(email);

        // Act + Assert
        String location = mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        // Assert
        assertNotNull(location);
        assertTrue(location.matches(".*/user/\\d+"));

        User savedUser = repository.findByEmail(email)
                .orElseThrow();

        assertAll(
                () -> assertNotNull(savedUser.getId()),
                () -> assertEquals("Marcelo", savedUser.getName()),
                () -> assertEquals(email, savedUser.getEmail()),
                () -> assertEquals("123456", savedUser.getPassword())
        );
    }

    @Test
    void shouldReturnBadRequestWhenEmailAlreadyExists() throws Exception {

        // Arrange
        User user = new User(
                null,
                "Marcelo",
                "duplicate@email.com",
                "123456"
        );

        repository.save(user);

        String requestBody = """
            {
                "name": "Outro Usuário",
                "email": "duplicate@email.com",
                "password": "654321"
            }
            """;

        // Act + Assert
        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUserWhenIdExists() throws Exception {

        // Arrange
        User user = repository.save(
                new User(
                        null,
                        "Marcelo",
                        "get@email.com",
                        "123456"
                )
        );

        // Act + Assert
        mockMvc.perform(
                        get("/user/{id}", user.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value("Marcelo"))
                .andExpect(jsonPath("$.email").value("get@email.com"));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {

        // Act + Assert
        mockMvc.perform(
                        get("/user/{id}", 9999)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateUserSuccessfully() throws Exception {

        // Arrange
        User user = repository.save(
                new User(
                        null,
                        "Marcelo",
                        "update@email.com",
                        "123456"
                )
        );

        String requestBody = """
            {
                "name": "Marcelo Atualizado",
                "email": "update@email.com",
                "password": "654321"
            }
            """;

        // Act + Assert
        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value("Marcelo Atualizado"))
                .andExpect(jsonPath("$.email").value("update@email.com"));
    }

    @Test
    void shouldDeleteUserSuccessfully() throws Exception {

        // Arrange
        User user = repository.save(
                new User(
                        null,
                        "Marcelo",
                        "delete@email.com",
                        "123456"
                )
        );

        // Act + Assert
        mockMvc.perform(
                        delete("/user/{id}", user.getId())
                )
                .andExpect(status().isNoContent());

        // Assert
        assertTrue(repository.findById(user.getId()).isEmpty());
    }
}