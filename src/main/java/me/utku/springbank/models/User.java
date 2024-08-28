package me.utku.springbank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.utku.springbank.auth.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private long ssn;
    private String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;
    private BigDecimal cash;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User(String firstName, String lastName, long ssn, String password, BigDecimal cash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.password = password;
        this.cash = cash;
    }

    public User() {
    }

    @Override
    public String getUsername() {
        return "";
    }
}
