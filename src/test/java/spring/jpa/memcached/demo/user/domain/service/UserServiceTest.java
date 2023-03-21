package spring.jpa.memcached.demo.user.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.jpa.memcached.demo.user.SpringBaseTest;
import spring.jpa.memcached.demo.user.domain.error.UserAlreadyExistAuthenticationException;
import spring.jpa.memcached.demo.user.domain.model.User;
import spring.jpa.memcached.demo.user.domain.repository.UserRepository;

import java.rmi.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends SpringBaseTest {

    final UserService userService;

    final UserRepository userRepository;

    User user;
    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setupUser() {
        user = new User();
        user.setEmail("toccara@example.com");
        user.setPassword("2dp4fw16h50f");
        user.setUsername("kurt");
        user.setRole("USER");
    }

    @Test
    void registerNewUserAccount() throws ServerException, UserAlreadyExistAuthenticationException {
        User result = userService.registerNewUserAccount(user);
        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        user.setId(result.getId());
    }

    @Test
    void processUserLogin() {
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteById(user.getId());
    }
}