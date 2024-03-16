package com.example.tienda_calzados.infra.security;

import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usersRepository.getUserData(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password del empleado es inválidos");
        }
        return usuario;
    }
}
