package com.platzi.market.domain.dto;

//NOTA: Esta clase se encarga de enviar la información necesaria para crear un JSON WEBTOKEN (JWT) DENTRO DE NUESTRO CONTROLADOR

public class AuthenticationResponse {
    private String jwt;
    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
