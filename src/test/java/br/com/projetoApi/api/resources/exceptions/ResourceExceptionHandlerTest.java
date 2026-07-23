package br.com.projetoApi.api.resources.exceptions;

import br.com.projetoApi.api.services.exceptions.DataIntegratyViolationException;
import br.com.projetoApi.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResourceExceptionHandlerTest {

    private static final String OBJECT_NOT_FOUND = "Objeto não encontrado";
    private static final String EMAIL_ALREADY_REGISTERED = "E-mail já cadastrado";

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @Test
    void shouldReturnNotFoundWhenObjectNotFoundExceptionOccurs() {

        // Arrange
        ObjectNotFoundException exception =
                new ObjectNotFoundException(OBJECT_NOT_FOUND);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/user/1");

        // Act
        ResponseEntity<StandardError> response =
                exceptionHandler.objectNotFound(exception, request);

        // Assert
        assertNotNull(response);

        StandardError error = response.getBody();
        assertNotNull(error);

        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), error.getStatus()),
                () -> assertEquals(OBJECT_NOT_FOUND, error.getError()),
                () -> assertEquals("/user/1", error.getPath()),
                () -> assertNotNull(error.getTimestamp())
        );
    }

    @Test
    void shouldReturnBadRequestWhenDataIntegrityViolationOccurs() {

        // Arrange
        DataIntegratyViolationException exception =
                new DataIntegratyViolationException(EMAIL_ALREADY_REGISTERED);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/user");

        // Act
        ResponseEntity<StandardError> response =
                exceptionHandler.dataIntegrityViolationException(exception, request);

        // Assert
        assertNotNull(response);

        StandardError error = response.getBody();
        assertNotNull(error);

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), error.getStatus()),
                () -> assertEquals(EMAIL_ALREADY_REGISTERED, error.getError()),
                () -> assertEquals("/user", error.getPath()),
                () -> assertNotNull(error.getTimestamp())
        );
    }
}