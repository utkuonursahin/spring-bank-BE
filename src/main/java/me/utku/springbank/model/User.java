package me.utku.springbank.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.enums.auth.Role;
import me.utku.springbank.generic.BaseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String ssn;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> authorities = Set.of(Role.ROLE_USER);

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(String firstName, String lastName, String ssn, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String getUsername() {
        return "";
    }

    public static class UserBuilder {
        public User build() {
            return new User(this.firstName, this.lastName, this.ssn, this.password);
        }
    }
}
