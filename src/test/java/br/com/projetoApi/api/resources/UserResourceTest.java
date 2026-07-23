package br.com.projetoApi.api.resources;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.domain.dto.UserDTO;
import br.com.projetoApi.api.factory.UserFactory;
import br.com.projetoApi.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = UserFactory.validUser();
        userDTO = UserFactory.validUserDTO();
    }

    @Test
    void shouldReturnUserWhenIdExists() {

        // Arrange
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = resource.findById(user.getId());

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(userDTO.getId(), response.getBody().getId()),
                () -> assertEquals(userDTO.getName(), response.getBody().getName()),
                () -> assertEquals(userDTO.getEmail(), response.getBody().getEmail()),
                () -> assertEquals(userDTO.getPassword(), response.getBody().getPassword())
        );

        verify(service).findById(user.getId());
        verify(mapper).map(user, UserDTO.class);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void shouldReturnAllUsers() {

        // Arrange
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        ResponseEntity<List<UserDTO>> response = resource.findAll();

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());

        List<UserDTO> users = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(1, users.size()),
                () -> assertEquals(userDTO.getId(), users.get(0).getId()),
                () -> assertEquals(userDTO.getName(), users.get(0).getName()),
                () -> assertEquals(userDTO.getEmail(), users.get(0).getEmail())
        );

        verify(service).findAll();
        verify(mapper).map(user, UserDTO.class);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void shouldCreateUserSuccessfully() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.create(any(UserDTO.class))).thenReturn(user);

        // Act
        ResponseEntity<Void> response = resource.create(userDTO);

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getHeaders().getLocation())
        );

        verify(service).create(userDTO);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateUserSuccessfully() {

        // Arrange
        when(service.update(any(UserDTO.class))).thenReturn(user);
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = resource.update(user.getId(), userDTO);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(userDTO.getId(), response.getBody().getId()),
                () -> assertEquals(userDTO.getName(), response.getBody().getName()),
                () -> assertEquals(userDTO.getEmail(), response.getBody().getEmail())
        );

        verify(service).update(any(UserDTO.class));
        verify(mapper).map(user, UserDTO.class);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void shouldDeleteUserSuccessfully() {

        // Arrange
        doNothing().when(service).delete(anyInt());

        // Act
        ResponseEntity<Void> response = resource.delete(user.getId());

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()),
                () -> assertNull(response.getBody())
        );

        verify(service).delete(user.getId());
        verifyNoMoreInteractions(service);
    }
}