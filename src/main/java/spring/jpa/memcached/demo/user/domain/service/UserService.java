package spring.jpa.memcached.demo.user.domain.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring.jpa.memcached.demo.user.domain.error.UserAlreadyExistAuthenticationException;
import spring.jpa.memcached.demo.user.domain.model.User;

import java.rmi.ServerException;

public interface UserService extends UserDetailsService {

    User registerNewUserAccount(User user) throws UserAlreadyExistAuthenticationException, ServerException;

    User processUser(String email, String password);

}
