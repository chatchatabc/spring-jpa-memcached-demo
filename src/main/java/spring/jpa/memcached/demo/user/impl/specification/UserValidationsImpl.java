package spring.jpa.memcached.demo.user.impl.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.memcached.demo.user.domain.repository.UserRepository;
import spring.jpa.memcached.demo.user.domain.specification.UserValidations;

@Service
public class UserValidationsImpl implements UserValidations {

    UserRepository userRepository;

    @Autowired
    public UserValidationsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
