package com.victor.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	public static final String SECRET_KEY = "bHJsbGxsZGxoZWhXb2Rob2xsaGRsb1dlb2xlcmhkbGxvb2Ryb1dXZHJXbG9kbGVsbGxsbHJsV2xvb2xlbG9Xbw==";
	
	public Key getKey() {
		byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, String user) {
		final String username = getUsernameFromToken(token);
		System.out.println(username.toString());
		return (username.equals(user) && !isTokenExpired(token));
	}

	public Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		
		return getExpiration(token).before(new Date());
	}

}
