package com.example.tienda_calzados.infra.security;

import com.example.tienda_calzados.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = userRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password del empleado es inválidos");
        }
        return usuario;
    }
}
