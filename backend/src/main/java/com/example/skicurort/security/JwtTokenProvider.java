package com.example.skicurort.security;

import com.example.skicurort.exception.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final AppSecurityConfigProperties appSecurityConfig;

  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate =
        new Date(currentDate.getTime() + appSecurityConfig.getJwtExpirationMilliseconds());

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, appSecurityConfig.getJwtSecret())
        .compact();
  }

  public String getUsernameFromJWT(String token) {
    Claims claims =
        Jwts.parser()
            .setSigningKey(appSecurityConfig.getJwtSecret())
            .parseClaimsJws(token)
            .getBody();
    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(appSecurityConfig.getJwtSecret()).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      throw new BadRequestException("Invalid JWT signature", ex);
    } catch (MalformedJwtException ex) {
      throw new BadRequestException("Invalid JWT token", ex);
    } catch (ExpiredJwtException ex) {
      throw new BadRequestException("Expired JWT token", ex);
    } catch (UnsupportedJwtException ex) {
      throw new BadRequestException("Unsupported JWT token", ex);
    } catch (IllegalArgumentException ex) {
      throw new BadRequestException("JWT claims string is empty.", ex);
    }
  }
}
