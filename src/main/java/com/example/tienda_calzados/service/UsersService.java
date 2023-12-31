package com.example.tienda_calzados.service;

import com.example.tienda_calzados.infra.errors.IntegrityValidation;
import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.model.users.RegisterUser;
import com.example.tienda_calzados.model.users.ResponseUserRegister;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.UsersRepository;
import com.example.tienda_calzados.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    List<RegisterValidation<RegisterUser>> validadores;

    public ResponseUserRegister saveUser(RegisterUser data) {
        if (roleRepository.findById(data.idRole()).isEmpty()) {
            throw new IntegrityValidation("El rol ingresado no existe");
        }

        validadores.forEach(v -> v.validation(data));
        var role = roleRepository.findById(data.idRole()).get();
        Users emp = usersRepository.save(new Users(data, role, passwordEncoder));

        return new ResponseUserRegister(emp);
    }

    public ResponseEntity<List<Users>> obtenerDatos() {
        var emp = usersRepository.findAll();
        return ResponseEntity.ok(emp);
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        var emp = usersRepository.getReferenceById(id);
        emp.desactivateAccount();
        return ResponseEntity.noContent().build();
    }

    public Users getUser(Long id) {
        return usersRepository.getReferenceById(id);
    }
}
