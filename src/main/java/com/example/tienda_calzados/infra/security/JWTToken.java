package com.example.tienda_calzados.infra.security;

public record JWTToken(
        String message,
        String jwTtoken
) {
}
