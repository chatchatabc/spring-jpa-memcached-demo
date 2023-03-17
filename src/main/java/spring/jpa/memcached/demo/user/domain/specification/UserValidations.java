package spring.jpa.memcached.demo.user.domain.specification;

import org.springframework.stereotype.Service;

@Service
public interface UserValidations {
    boolean emailExists(String email);
}
