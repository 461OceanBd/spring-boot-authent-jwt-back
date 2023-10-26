package com.rmrtechs.springbootauthentjwtback.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

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
    	
		http.cors(cors -> {
			cors.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			});
		}).csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(
						(requests) -> requests.requestMatchers("/api/authent/**", "/api-docs/**", "/swagger-ui/**")  // TODO : faire role pour acces swagger + Accès en dev uniquement
								.permitAll().requestMatchers("api/**").authenticated())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
    }

    
    /**
     * 
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    	
    }

}
