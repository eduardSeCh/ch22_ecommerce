package org.generation.app.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//14. TODO generar el token para los usuarios authenticados
public class JWTAthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	//Atenticar al suario
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException {
		
		AuthCredentials authCredential = new AuthCredentials();
		try {
			authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredential.getEmail(),  //Username
				authCredential.getPassword(),//Password
				Collections.emptyList()//Credenciales (roles)
				);
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	//Una vez atenticado, generar el token
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getFullName(), userDetails.getUsername()); // Generar token
		
		response.addHeader("Authorization", "Bearer" + token);
		response.getWriter().close(); // cambie flush por close;
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
}
