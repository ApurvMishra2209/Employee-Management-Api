package com.employee.management.util;


import com.employee.management.domain.Employee;
import com.employee.management.model.Role;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;



@Component
public class JwtUtils {

    private String SECRET_KEY= "secret";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY ).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserRole", employee.getRole());
        claims.put("UserId",employee.getId());
        return doGenerateToken(claims, employee.getUuid());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public static Role getRole(final String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        JsonElement role = jsonObject.get("UserRole");
        String setRole = null;
            if (role.getAsString().equals("ADMIN") || role.getAsString().equals("admin")) {
                return Role.ADMIN;
            } else {
                setRole = role.getAsString();
            }
        if (setRole.equals("MANAGER") || setRole.equals("manager")) {
            return Role.MANAGER;
        } else {
            return Role.EMPLOYEE;
        }
    }
    
}



