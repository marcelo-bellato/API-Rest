package br.com.projetoApi.api.services.impl;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.domain.dto.UserDTO;
import br.com.projetoApi.api.factory.UserFactory;
import br.com.projetoApi.api.repositories.UserRepository;
import br.com.projetoApi.api.services.exceptions.DataIntegratyViolationException;
import br.com.projetoApi.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String OBJECT_NOT_FOUND = "Objeto não encontrado";
    private static final String EMAIL_ALREADY_EXISTS = "E-mail já cadastrado no sistema";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        user = UserFactory.validUser();
        userDTO = UserFactory.validUserDTO();
        optionalUser = UserFactory.validOptionalUser();
    }

    @Test
    void shouldReturnUserWhenIdExists() {

        // Arrange
        when(repository.findById(user.getId()))
                .thenReturn(optionalUser);

        // Act
        User response = service.findById(user.getId());

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getName(), response.getName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertEquals(user.getPassword(), response.getPassword())
        );

        verify(repository).findById(user.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenUserDoesNotExist() {

        // Arrange
        Integer userId = user.getId();

        when(repository.findById(userId))
                .thenReturn(Optional.empty());

        // Act
        ObjectNotFoundException exception = assertThrows(
                ObjectNotFoundException.class,
                () -> service.findById(userId)
        );

        // Assert
        assertEquals(OBJECT_NOT_FOUND, exception.getMessage());

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnAllUsers() {

        // Arrange
        when(repository.findAll())
                .thenReturn(List.of(user));

        // Act
        List<User> response = service.findAll();

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(1, response.size()),
                () -> assertEquals(user.getId(), response.get(0).getId()),
                () -> assertEquals(user.getName(), response.get(0).getName()),
                () -> assertEquals(user.getEmail(), response.get(0).getEmail()),
                () -> assertEquals(user.getPassword(), response.get(0).getPassword())
        );

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldCreateUserSuccessfully() {

        // Arrange
        when(repository.findByEmail(userDTO.getEmail()))
                .thenReturn(Optional.empty());

        when(mapper.map(userDTO, User.class))
                .thenReturn(user);

        when(repository.save(user))
                .thenReturn(user);

        // Act
        User response = service.create(userDTO);

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getName(), response.getName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertEquals(user.getPassword(), response.getPassword())
        );

        verify(repository).findByEmail(userDTO.getEmail());
        verify(mapper).map(userDTO, User.class);
        verify(repository).save(user);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void shouldThrowDataIntegrityViolationExceptionWhenEmailAlreadyExistsOnCreate() {

        // Arrange
        optionalUser.get().setId(2);

        when(repository.findByEmail(userDTO.getEmail()))
                .thenReturn(optionalUser);

        // Act
        DataIntegratyViolationException exception = assertThrows(
                DataIntegratyViolationException.class,
                () -> service.create(userDTO)
        );

        // Assert
        assertEquals(EMAIL_ALREADY_EXISTS, exception.getMessage());

        verify(repository).findByEmail(userDTO.getEmail());
        verify(repository, never()).save(any(User.class));
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldUpdateUserSuccessfully() {

        // Arrange
        when(repository.findByEmail(userDTO.getEmail()))
                .thenReturn(Optional.empty());

        when(mapper.map(userDTO, User.class))
                .thenReturn(user);

        when(repository.save(user))
                .thenReturn(user);

        // Act
        User response = service.update(userDTO);

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getName(), response.getName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertEquals(user.getPassword(), response.getPassword())
        );

        verify(repository).findByEmail(userDTO.getEmail());
        verify(mapper).map(userDTO, User.class);
        verify(repository).save(user);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void shouldUpdateUserWhenEmailBelongsToSameUser() {

        // Arrange
        optionalUser.get().setId(userDTO.getId());

        when(repository.findByEmail(userDTO.getEmail()))
                .thenReturn(optionalUser);

        when(mapper.map(userDTO, User.class))
                .thenReturn(user);

        when(repository.save(user))
                .thenReturn(user);

        // Act
        User response = service.update(userDTO);

        // Assert
        assertNotNull(response);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getEmail(), response.getEmail())
        );

        verify(repository).findByEmail(userDTO.getEmail());
        verify(mapper).map(userDTO, User.class);
        verify(repository).save(user);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void shouldThrowDataIntegrityViolationExceptionWhenUpdatingWithExistingEmail() {

        // Arrange
        optionalUser.get().setId(2);

        when(repository.findByEmail(userDTO.getEmail()))
                .thenReturn(optionalUser);

        // Act
        DataIntegratyViolationException exception = assertThrows(
                DataIntegratyViolationException.class,
                () -> service.update(userDTO)
        );

        // Assert
        assertEquals(EMAIL_ALREADY_EXISTS, exception.getMessage());

        verify(repository).findByEmail(userDTO.getEmail());
        verify(repository, never()).save(any(User.class));
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldDeleteUserSuccessfully() {

        // Arrange
        when(repository.findById(user.getId()))
                .thenReturn(optionalUser);

        // Act
        service.delete(user.getId());

        // Assert
        verify(repository).findById(user.getId());
        verify(repository).deleteById(user.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingUser() {

        // Arrange
        when(repository.findById(user.getId()))
                .thenReturn(Optional.empty());

        // Act
        ObjectNotFoundException exception = assertThrows(
                ObjectNotFoundException.class,
                () -> service.delete(user.getId())
        );

        // Assert
        assertEquals(OBJECT_NOT_FOUND, exception.getMessage());

        verify(repository).findById(user.getId());
        verify(repository, never()).deleteById(anyInt());
        verifyNoMoreInteractions(repository);
    }
}