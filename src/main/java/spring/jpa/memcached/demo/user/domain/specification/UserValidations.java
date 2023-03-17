package spring.jpa.memcached.demo.user.domain.specification;

public interface UserValidations {
    boolean emailExists(String email);
}
