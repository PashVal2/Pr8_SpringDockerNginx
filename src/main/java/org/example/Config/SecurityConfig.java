package org.example.Config;

import org.example.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean // шифрование пароля
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // проверка пользователся
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);  // Здесь сервис
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean // настройки аунтефикации
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeRequests(auth -> {
                    auth.antMatchers(
                            "/", "/sort", "/svg", "/author", "/serverInfo", "/sessionPage", "/fileList", "/upload", "/makeChart",
                            "/register", "/login", "/lombard", "/api/**", "/session/**", "/files/**", "/statistics",
                            "/css/**", "/html/**", "/js/**", "/icon/**", "/pdf/**", "/json/**", "/graph/**"
                    ).permitAll();
                    auth.antMatchers("/users").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form // обработка post запроса из...
                        .loginPage("/login")
                        .usernameParameter("name")
                        .loginProcessingUrl("/login")
                        .permitAll()
                        .defaultSuccessUrl("/lombard", true)
                ) //
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/lombard")
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
