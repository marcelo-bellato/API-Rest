package br.com.projetoapi.api.resources;

import br.com.projetoapi.api.domain.User;
import br.com.projetoapi.api.domain.dto.UserDTO;
import br.com.projetoapi.api.factory.UserFactory;
import br.com.projetoapi.api.services.UserService;
import br.com.projetoapi.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserResource.class)
class UserResourceMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = UserFactory.validUser();
        userDTO = UserFactory.validUserDTO();
    }

    @Test
    void shouldReturnUserWhenIdExists() throws Exception {

        // Arrange
        when(service.findById(user.getId()))
                .thenReturn(user);

        when(mapper.map(user, UserDTO.class))
                .thenReturn(userDTO);

        // Act + Assert
        mockMvc.perform(
                        get("/user/{id}", user.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(userDTO.getId()))
                .andExpect(jsonPath("$.name")
                        .value(userDTO.getName()))
                .andExpect(jsonPath("$.email")
                        .value(userDTO.getEmail()));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {

        // Arrange
        when(service.findById(user.getId()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        // Act + Assert
        mockMvc.perform(
                        get("/user/{id}", user.getId())
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnAllUsers() throws Exception {

        // Arrange
        when(service.findAll())
                .thenReturn(List.of(user));

        when(mapper.map(user, UserDTO.class))
                .thenReturn(userDTO);

        // Act + Assert
        mockMvc.perform(
                        get("/user")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id")
                        .value(userDTO.getId()))
                .andExpect(jsonPath("$[0].name")
                        .value(userDTO.getName()))
                .andExpect(jsonPath("$[0].email")
                        .value(userDTO.getEmail()));
    }

    @Test
    void shouldCreateUserSuccessfully() throws Exception {

        // Arrange
        when(service.create(any(UserDTO.class)))
                .thenReturn(user);

        // Act + Assert
        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    {
                                      "name": "Marcelo",
                                      "email": "marcelo@email.com",
                                      "password": "123456"
                                    }
                                    """)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(
                        "Location",
                        endsWith("/user/" + user.getId())
                ));
    }

    @Test
    void shouldUpdateUserSuccessfully() throws Exception {

        // Arrange
        when(service.update(any(UserDTO.class)))
                .thenReturn(user);

        when(mapper.map(user, UserDTO.class))
                .thenReturn(userDTO);

        // Act + Assert
        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    {
                                      "name": "Marcelo Atualizado",
                                      "email": "marcelo@email.com",
                                      "password": "123456"
                                    }
                                    """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(userDTO.getId()))
                .andExpect(jsonPath("$.name")
                        .value(userDTO.getName()))
                .andExpect(jsonPath("$.email")
                        .value(userDTO.getEmail()));
    }

    @Test
    void shouldDeleteUserSuccessfully() throws Exception {

        // Arrange
        doNothing().when(service).delete(user.getId());

        // Act + Assert
        mockMvc.perform(
                        delete("/user/{id}", user.getId())
                )
                .andExpect(status().isNoContent());

        verify(service).delete(user.getId());
    }

    @ParameterizedTest
    @MethodSource("invalidUserData")
    void shouldReturnBadRequestWhenCreatingUserWithInvalidData(
            String payload,
            String expectedMessage) throws Exception {

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validation error"))
                .andExpect(jsonPath("$.path").value("/user"))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages", hasItem(expectedMessage)));

        verifyNoInteractions(service);
    }

    static Stream<Arguments> invalidUserData() {
        return Stream.of(
                Arguments.of("""
                    {
                      "name": "",
                      "email": "marcelo@email.com",
                      "password": "123456"
                    }
                    """, "Nome é obrigatório"),

                Arguments.of("""
                    {
                      "name": "Marcelo",
                      "email": "email-invalido",
                      "password": "123456"
                    }
                    """, "E-mail inválido"),

                Arguments.of("""
                    {
                      "name": "Marcelo",
                      "email": "marcelo@email.com",
                      "password": "123"
                    }
                    """, "A senha deve ter no mínimo 6 caracteres"),

                Arguments.of("""
                    {
                      "name": "",
                      "email": "",
                      "password": ""
                    }
                    """, "Nome é obrigatório")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUserDataForUpdate")
    void shouldReturnBadRequestWhenUpdatingUserWithInvalidData(
            String payload,
            String expectedMessage) throws Exception {

        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validation error"))
                .andExpect(jsonPath("$.path")
                        .value("/user/" + user.getId()))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages", hasItem(expectedMessage)));

        verifyNoInteractions(service);
    }

    static Stream<Arguments> invalidUserDataForUpdate() {
        return Stream.of(
                Arguments.of("""
                    {
                      "name": "Marcelo",
                      "email": "email-invalido",
                      "password": "123456"
                    }
                    """, "E-mail inválido"),

                Arguments.of("""
                    {
                      "name": "",
                      "email": "marcelo@email.com",
                      "password": "123456"
                    }
                    """, "Nome é obrigatório"),

                Arguments.of("""
                    {
                      "name": "Marcelo",
                      "email": "marcelo@email.com",
                      "password": "123"
                    }
                    """, "A senha deve ter no mínimo 6 caracteres")
        );
    }
}
