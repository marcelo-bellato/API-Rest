package br.com.projetoapi.api.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserUsingSetters() {
        User user = new User();

        user.setId(1);
        user.setName("Marcelo");
        user.setEmail("marcelo@email.com");
        user.setPassword("123456");

        assertEquals(1, user.getId());
        assertEquals("Marcelo", user.getName());
        assertEquals("marcelo@email.com", user.getEmail());
        assertEquals("123456", user.getPassword());
    }

    @Test
    void shouldCompareUsers() {
        User user1 = new User(1, "Marcelo", "marcelo@email.com", "123456");
        User user2 = new User(1, "Marcelo", "marcelo@email.com", "123456");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotNull(user1.toString());
    }
}