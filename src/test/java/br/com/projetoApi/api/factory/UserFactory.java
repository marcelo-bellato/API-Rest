package br.com.projetoApi.api.factory;

import br.com.projetoApi.api.domain.User;
import br.com.projetoApi.api.domain.dto.UserDTO;

import java.util.Optional;

public final class UserFactory {

    private static final Integer ID = 1;
    private static final Integer DUPLICATED_ID = 2;

    private static final String NAME = "Marcelo";
    private static final String ANOTHER_NAME = "João";

    private static final String EMAIL = "marcelo@mail.com";
    private static final String EMPTY_EMAIL = "";

    private static final String PASSWORD = "123";
    private static final String ANOTHER_PASSWORD = "123456";

    private UserFactory() {
    }

    public static User validUser() {
        return buildUser(ID, NAME, EMAIL, PASSWORD);
    }

    public static UserDTO validUserDTO() {
        User user = validUser();

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static Optional<User> validOptionalUser() {
        return Optional.of(validUser());
    }

    public static User userWithDuplicatedEmail() {
        return buildUser(
                DUPLICATED_ID,
                ANOTHER_NAME,
                EMAIL,
                ANOTHER_PASSWORD
        );
    }

    public static UserDTO userDTOWithDuplicatedEmail() {
        User user = userWithDuplicatedEmail();

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User userWithEmptyEmail() {
        return buildUser(
                ID,
                NAME,
                EMPTY_EMAIL,
                PASSWORD
        );
    }

    public static UserDTO userDTOWithEmptyEmail() {
        User user = userWithEmptyEmail();

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    private static User buildUser(
            Integer id,
            String name,
            String email,
            String password) {

        return new User(
                id,
                name,
                email,
                password
        );
    }
}