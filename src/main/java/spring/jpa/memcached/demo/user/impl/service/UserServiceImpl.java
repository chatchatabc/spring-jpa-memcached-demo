package spring.jpa.memcached.demo.user.impl.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.jpa.memcached.demo.user.domain.error.UserAlreadyExistAuthenticationException;
import spring.jpa.memcached.demo.user.domain.model.User;
import spring.jpa.memcached.demo.user.domain.repository.UserRepository;
import spring.jpa.memcached.demo.user.domain.service.UserService;
import spring.jpa.memcached.demo.user.domain.specification.UserValidations;

import java.rmi.ServerException;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    final UserRepository userRepository;

    final UserValidations userValidations;

    final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserValidations userValidations, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userValidations = userValidations;
    }

    public User processUserLogin(String email, String password) throws UsernameNotFoundException {
        User user = this.loadUserByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UsernameNotFoundException("Wrong Password");
        }

        return user;
    }

    public User registerNewUserAccount(User user) throws UserAlreadyExistAuthenticationException, ServerException {
        if (userValidations.emailExists(user.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("There is an account with that email address: "
                    + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        user.setIsEnabled(true);
        user.setIsAccountNonLocked(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsCredentialsNonExpired(true);
        user.getAuthorities();

        return user;
    }
}
