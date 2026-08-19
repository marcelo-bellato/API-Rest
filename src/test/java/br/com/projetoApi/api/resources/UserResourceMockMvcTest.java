package br.com.projetoApi.api.resources;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.domain.dto.UserDTO;
import br.com.projetoApi.api.factory.UserFactory;
import br.com.projetoApi.api.services.UserService;
import br.com.projetoApi.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

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

    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "",
                                  "email": "marcelo@email.com",
                                  "password": "123456"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "Marcelo",
                                  "email": "email-invalido",
                                  "password": "123456"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenPasswordIsTooShort() throws Exception {

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "Marcelo",
                                  "email": "marcelo@email.com",
                                  "password": "123"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenRequiredFieldsAreMissing() throws Exception {

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "",
                                  "email": "",
                                  "password": ""
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithInvalidEmail() throws Exception {

        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "Marcelo",
                                  "email": "email-invalido",
                                  "password": "123456"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithBlankName() throws Exception {

        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "",
                                  "email": "marcelo@email.com",
                                  "password": "123456"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithShortPassword() throws Exception {

        mockMvc.perform(
                        put("/user/{id}", user.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "name": "Marcelo",
                                  "email": "marcelo@email.com",
                                  "password": "123"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

}
