package com.example.tienda_calzados.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tienda_calzados.model.users.customer.Customers;
import com.example.tienda_calzados.model.users.employee.Employees;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String SECRET_KEY;

    public String generarTokenEmp(Employees emp) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("tienda_calzados")
                    .withSubject(emp.getUsername())
                    .withClaim("id", emp.getId())
                    .withClaim("role", emp.getRole().getNombre())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String generarTokenCustomer(Customers customers) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("tienda_calzados")
                    .withClaim("id", customers.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Ocurrio un error");
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            verifier = JWT.require(algorithm)
                    .withIssuer("tienda_calzados")
                    .build()
                    .verify(token);

            verifier.getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        assert verifier != null;
        if (verifier.getSubject() == null) {
            throw new RuntimeException("El usuario es invalido");
        }

        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-05:00"));
    }
}
