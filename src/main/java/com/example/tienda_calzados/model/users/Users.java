package com.example.tienda_calzados.model.users;

import com.example.tienda_calzados.model.Role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60, nullable = false)
    protected String name;
    @Column(length = 60, nullable = false)
    protected String lastname;
    @Column(length = 70, nullable = false)
    protected String email;
    @Column(name = "birthday")
    protected LocalDate birthdate;
    @Column(length = 60, nullable = false)
    protected String password;
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    protected Boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Users(RegisterUser data, Role rol, BCryptPasswordEncoder passwordEncoder) {
        this.name = data.name();
        this.lastname = data.lastname();
        this.email = data.email();
        this.birthdate = data.birthdate();
        this.role = rol;
        this.active = true;
        this.password = passwordEncoder.encode(data.password());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().getNombre()));
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

    @Override
    public boolean isEnabled() {
        return getActive();
    }

    public void desactivateAccount() {
        this.active = false;
    }

    private <T> void assignIfNotNull(T value, Method setter) {
        if (value != null) {
            try {
                setter.invoke(this, value);
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones apropiado
            }
        }
    }

    public void updateUserData(UpdateUser data) {
        try {
            assignIfNotNull(data.name(), getClass().getMethod("setName", String.class));
            assignIfNotNull(data.lastname(), getClass().getMethod("setLastname", String.class));
            assignIfNotNull(data.email(), getClass().getMethod("setEmail", String.class));
            assignIfNotNull(data.birthdate(), getClass().getMethod("setBirthdate", LocalDate.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
