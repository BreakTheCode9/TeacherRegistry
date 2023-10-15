package com.example.teacherregistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/teachers/get-all").permitAll() // Public access
                .antMatchers("/api/teachers/get/**").permitAll() // Public access
                .antMatchers("/api/teachers/new").hasRole("ADMIN") // Requires ADMIN role
                .antMatchers("/api/teachers/delete/**").hasRole("ADMIN") // Requires ADMIN role
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // Use HTTP Basic Authentication
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // For demo purposes; use a real encoder in production.
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("password").roles("ADMIN").build());
        return manager;
    }

}
