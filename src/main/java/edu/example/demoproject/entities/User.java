package edu.example.demoproject.entities;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 128, name="user_id")
    private Long id;

    @Column(length = 128, name="full_name")
    private String fullName;

    @Column(length = 128, name="account_name")
    private String accountName;

    @Column(length = 128)
    private String email;

    @Column(length = 128)
    private String password;

    @Column(name="enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(name="users_roles",
            joinColumns={@JoinColumn(name="users", referencedColumnName="user_id")},
            inverseJoinColumns={@JoinColumn(name="roles", referencedColumnName="role_id")})
    @Column(name="roles")
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("ADMIN"),
                new SimpleGrantedAuthority("MANAGER"),
                new SimpleGrantedAuthority("CUSTOMER"),
                new SimpleGrantedAuthority("GUEST"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
