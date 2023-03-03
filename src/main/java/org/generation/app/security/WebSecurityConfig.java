package org.generation.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 1. personalizar seguridad (crear clase)
// 2. probar deshabilitando la seguridad .permiAll()
// 3. Habilitar la seguridad .athenticated()
// 4. Crear usuario y contraseña en memorya
// 5. Crear el bean para encriptar password
// 6. Encriptar password
// 7. Habiltar los roles en los endpoints - @EnableGlobalMethodSecurity(prePostEnabled = true)
// 8. autorizar para ciertos roles, los metodos de controler - @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
// 9. En memoria establecer el usuario clase UserDailtrsImpl - remplaza el paso 4
// 10. Leer los datos de user de DB con la clase UserDetailServiceImpl
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //Verificar si tienen permiso lo roles
public class WebSecurityConfig {
	
	@Autowired //11. inyectar la inerfaz
	private UserDetailsService userDetailService;
	@Autowired
	private JWTAthorizationFilter jwtAuthorizationfilter;
	
	 
	
	@Bean //toma cualquier solicitud http
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		
		JWTAthenticationFilter jwtAuthenticationFilter = new JWTAthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		http
			.httpBasic() //solicitud basica
			.and()
			.authorizeHttpRequests() //Autorizar solicitud http
			.anyRequest()//Cualquier solicitud
			.authenticated() //Pedir atorizacion
			.and()
			// generar el token para los usuarios authenticados
			.addFilter(jwtAuthenticationFilter)
			//TODO verioficar el token JWT poara las peticiones http
			.addFilterBefore(jwtAuthorizationfilter, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable(); //Desabilitar la inconeccion en cliente/servidor
		
		return http.build();
	}
	
	//@Bean //12. Deshabilitar la carga de usuarios en memoria
	
	  public UserDetailsService userDetailService() { UserDetails juan =
	  User.builder() .username("juan") .password("{noop}123") //TODO encriptar
	  .roles("ADMIN") .build();
	  
	  UserDetails luis = User.builder() .username("luis")
	  .password(passEncoder().encode("fierro")) //TODO encriptar .roles("USER")
	  .build();
	  
	  return new InMemoryUserDetailsManager(juan,luis); }
	 
	
	// paso 13: Incerceptamos el manejo de autenticación de los usuarios
		@Bean
		AuthenticationManager authManager(HttpSecurity http) throws Exception {
			return http
					.getSharedObject( AuthenticationManagerBuilder.class)
					.userDetailsService( userDetailService )
					.passwordEncoder( passEncoder() )
					.and()
					.build();
		}
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder(); //Encriptar password
	}
	/*
	 * public static void main(String[] args) { System.out.println("Passowrd: "+ new
	 * BCryptPasswordEncoder().encode("fierro")); }
	 */
	
}
