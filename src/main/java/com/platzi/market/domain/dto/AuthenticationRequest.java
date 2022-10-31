package com.platzi.market.domain.dto;

//NOTA: Esta clase se encarga de recibir la información necesaria para crear un JSON WEBTOKEN (JWT) DENTRO DE NUESTRO CONTROLADOR

public class AuthenticationRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
