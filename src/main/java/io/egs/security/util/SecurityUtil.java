package io.egs.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.asm.ClassReader;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityUtil {

	private String secretKey="SECRET";

	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private <T> T extractClaim(String token, Function<Claims, T>claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token).getBody();
	}
	
	public String generatetoken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,user.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
		.signWith(SignatureAlgorithm.HS256, secretKey)
		.compact();
	}
}
