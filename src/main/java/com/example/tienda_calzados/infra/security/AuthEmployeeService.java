package com.example.tienda_calzados.infra.security;

import com.example.tienda_calzados.infra.errors.UserNotActive;
import com.example.tienda_calzados.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthEmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = employeeRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password del empleado es inválidos");
        }
        return usuario;
    }
}
