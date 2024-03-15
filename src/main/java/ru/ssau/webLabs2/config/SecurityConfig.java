package ru.ssau.webLabs2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Value("${security.logins}")
    private List<String> logins;
    @Value("${security.passwords}")
    private List<String> passwords;
    @Value("${security.roles}")
    private List<String> roles;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/projects/**").hasRole(roles.get(1))
                        .requestMatchers(HttpMethod.PUT, "/projects/**").hasRole(roles.get(1))
                        .requestMatchers(HttpMethod.DELETE, "/projects/**").hasRole(roles.get(1))
                        .anyRequest().authenticated()
                )
               .httpBasic(Customizer.withDefaults())
               .csrf(AbstractHttpConfigurer::disable)
               .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername(logins.get(0))
                        .password(passwordEncoder().encode(passwords.get(0)))
                        .roles(roles.get(0))
                        .build();
        UserDetails admin =
                User.withUsername(logins.get(1))
                        .password(passwordEncoder().encode(passwords.get(1)))
                        .roles(roles.get(1))
                        .build();
        return new InMemoryUserDetailsManager(user, admin);

    }

}
