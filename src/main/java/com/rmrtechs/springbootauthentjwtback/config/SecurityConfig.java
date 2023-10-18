package com.rmrtechs.springbootauthentjwtback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	/**
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf((csrf) -> csrf.disable())
    	.authorizeHttpRequests((requests) -> requests
    			.requestMatchers("/api/authent/**", "/api-docs/**", "/swagger-ui/**").permitAll() // TODO : faire role pour acces swagger + Accès en dev uniquement
    			.requestMatchers("api/**").authenticated()
                )
    	.formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
    	return http.build();
    }

    
    /**
     * 
     * @return
     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//    	
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() { // TODO
		return NoOpPasswordEncoder.getInstance();
    	
    }
}
