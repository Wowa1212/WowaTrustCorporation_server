package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/style.css").permitAll() // Povolit přístup ke style.css
                        .anyRequest().authenticated() // Ostatní požadavky vyžadují přihlášení
                )
                .formLogin(form -> form
                        .loginPage("/login") // Vlastní přihlašovací stránka
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL pro odhlášení
                        .logoutSuccessUrl("/login") // Přesměrování na login po odhlášení
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // CSRF ochrana vypnutá (pro testování)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}