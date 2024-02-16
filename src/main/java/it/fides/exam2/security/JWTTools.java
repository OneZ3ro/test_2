package it.fides.exam2.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.fides.exam2.exceptions.UnauthorizedException;
import it.fides.exam2.models.entities.UtenteEntity;

import java.util.Date;

@Component
public class JWTTools {
	private String secret = "kdsjhfoi23434f-sdfsfsfs-skfjweweretpopmasd";

	public String createToken(UtenteEntity user) {

		return Jwts.builder().setSubject(String.valueOf(user.getIdUtente()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3_600_000))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

	}

	public void verifyToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception ex) {
			throw new UnauthorizedException("Il token non è valido! Per favore effettua nuovamente il login!");
		}

	}

	public String extractIdFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
