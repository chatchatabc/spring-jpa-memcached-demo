package spring.jpa.memcached.demo.user.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * password hash with BCrypt: plain password + salt
     */
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Size(min = 10, max = 20, message = "Email must be between 10 to 20 characters long")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate dateCreated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Transient
    private Boolean isAccountNonExpired;
    @Transient
    private Boolean isAccountNonLocked;
    @Transient
    private Boolean isEnabled;
    @Transient
    private Boolean isCredentialsNonExpired;

    @PrePersist
    public void prePersist() {
        if (this.dateCreated == null){
            this.dateCreated = LocalDate.from(LocalDateTime.now());
        }
        this.lastLogin = LocalDate.from(LocalDateTime.now());
    }

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