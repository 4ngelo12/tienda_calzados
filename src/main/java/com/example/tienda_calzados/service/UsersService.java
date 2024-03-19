package com.example.tienda_calzados.service;

import com.example.tienda_calzados.infra.errors.IntegrityValidation;
import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.*;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.UsersRepository;
import com.example.tienda_calzados.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Users getUserData(String token) {
        var jwtToken = token.replace("Bearer ", "");
        var nombreUsuario = tokenService.getSubject(jwtToken);

        return usersRepository.getUserData(nombreUsuario);
    }

    public Page<ListUserData> getAllUsers(Pageable paginacion) {
        return usersRepository.findAllByActiveTrue(paginacion).map(ListUserData::new);
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        var emp = usersRepository.getReferenceById(id);
        emp.desactivateAccount();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> activateUser(Long id) {
        var emp = usersRepository.getReferenceById(id);
        emp.activateAccount();
        return ResponseEntity.noContent().build();
    }

    public Users getUser(Long id) {
        return usersRepository.getReferenceById(id);
    }

    public ResponseUserUpdate userUpdate(UpdateUser data) {
        Users users = usersRepository.getReferenceById(data.id());
        users.updateUserData(data);

        return new ResponseUserUpdate(users);
    }

    public ResponseEntity<ResponseUserByEmail> getUserByEmail(String email) {
        var user = usersRepository.getUserData(email);
        Long id = user.getId();
        return ResponseEntity.ok().body(new ResponseUserByEmail(id));
    }

    public ResponseEntity<ResponseResetPassword> resetPassword(ResetPassword data) {
        var user = usersRepository.getReferenceById(data.id());
        user.resetPassword(data.password(), passwordEncoder);
        return ResponseEntity.ok().body(new ResponseResetPassword("Contraseña actualizada correctamente"));
    }
}
