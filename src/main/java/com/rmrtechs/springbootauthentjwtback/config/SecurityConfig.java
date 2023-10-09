package com.rmrtechs.springbootauthentjwtback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
    			.requestMatchers("/authenticated-page").authenticated()
                .requestMatchers("/home", "sign-in", "sign-up").permitAll())
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
    	return http.build();
    }

    
    /**
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // only for test.
    	
    }
}
