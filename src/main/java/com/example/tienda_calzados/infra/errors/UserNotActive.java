package com.example.tienda_calzados.infra.errors;

import org.springframework.security.core.AuthenticationException;

public class UserNotActive extends AuthenticationException {
    public UserNotActive(String s) {super(s);}
    public UserNotActive(String s, Throwable cause) {
        super(s, cause);
    }
}
