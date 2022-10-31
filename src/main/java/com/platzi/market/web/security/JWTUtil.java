package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "pl4tzi"; //Esta llave debe ser mas fuerte, por ejemplo colocamos una simple

    public String generateToken(UserDetails userDetails){
        //builder nos permite en una secuencia de metodos construir el JWT(JSON WEB TOKEN)
        return Jwts.builder()
                .setSubject(userDetails.getUsername())//Le añadimos el usuario mediante setSubject
                .setIssuedAt(new Date()) //Indicamos en que fecha fue creada mediante setIssuedAt
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //Indicamos Fecha de expiracion de 10 horas
                .signWith(SignatureAlgorithm.HS256, KEY) //Firma del método  Y la clave (La clave debe ser declara como variable de preferencia
                .compact(); //Por ultimo finalizamos con compact, para crear el JWT
    }

    /*Nota: Adicional, se debe crear un controlador que reciba como parámetro el usuario y contraseña
            ademas como respuesta debe enviar un JWT*/
    /*Previo a esto creamos 2 clases en el paquete DTO las cuales permitiran controlar la respuesta*/

    public boolean validaToken(String token, UserDetails userDetails){
        //El usuername extraido debe coincidir con el username respectivo y el token no debe haber expirado
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
