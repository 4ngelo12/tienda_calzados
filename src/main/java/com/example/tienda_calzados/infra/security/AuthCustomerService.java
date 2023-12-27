package com.example.tienda_calzados.infra.security;

import com.example.tienda_calzados.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthCustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = customerRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password del cliente es inválidos");
        }
        return usuario;
    }
}
