package spring.jpa.memcached.demo.user.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters long")
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Username must have no special characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * password hash with SHA-1: plain password + salt
     */
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Password must be SHA-1 hash")
    @Size(min = 3, max = 15, message = "Password must be between 6 to 15 characters long")
    private String password;

    @Size(min = 5, max = 10, message = "salt must be between 5 to 10 characters long")
    @Column(name = "salt", nullable = false)
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "salt must have no special characters")
    private String salt;

    @Email
    @Size(min = 10, max = 20, message = "Email must be between 10 to 20 characters long")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;
    @Column(name = "last_login", nullable = false)
    private LocalDate lastLogin;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isEnabled;
    private Boolean isCredentialsNonExpired;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}