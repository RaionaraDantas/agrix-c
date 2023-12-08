package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * The type Token service.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  /**
   * Instantiates a new Token service.
   *
   * @param secret the secret
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generate token string.
   *
   * @param person the person
   * @return the string
   */
  public String generateToken(Person person) {
    return JWT.create()
            .withIssuer("agrixc")
            .withSubject(person.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Validate token string.
   *
   * @param token the token
   * @return the string
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
            .withIssuer("agrixc")
            .build()
            .verify(token)
            .getSubject();
  }

}