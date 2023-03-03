package org.generation.app.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//15. Crear y verificar token JWT
public class TokenUtils {
	private final static String ACCESS_TOKEN_SECRET = "4dnVq-4lDu7dXVfYv42Xcfnrlj7oC3Cmf4Td-KLv?N?4MAfgIv62Q-!PWdlOswLlG1qFzy42sVAMouxvTmx7T-UmuH2G0MMyhZ?1Bq5Fl?ACNnlm9q=pzigsJwiLiWYYc?INgJgf9M46rf9kGmas/zqe8XDsH0h4!A1hTwFWumUgU5gZs!DqiZE5zb0XooK-45gXwmKF4aYXetoZgkA2G=ekXm/nbp!n?Mm3MHB3CCYsZd3bH-FyVC5JFmLXTt!u";
	private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 300L; // 5 min
	
	
	/*
	 * * Genera el token JWT y lo adjunta en el header de la respuesta HTTP
	 * */
	public static String createToken(String fullName,String email ) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", fullName);
		
		return Jwts //generar Jason token
				.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(payload)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())) //Firmar con el acces token
				.compact();
	}
	//18. Validar el token recibido por el cliente
	public static UsernamePasswordAuthenticationToken getAuthentication(String token){
		
		try {			
			Claims claims = Jwts
					.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email= claims.getSubject();
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList()); //collections-Roles
		} catch (Exception e) {
			System.out.println(e);
			return null; 
		}
		
	}
}
