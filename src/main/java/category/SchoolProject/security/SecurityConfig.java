package category.SchoolProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build()
                );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.formLogin(form->form.permitAll());
        security.authorizeHttpRequests(authorize-> authorize.requestMatchers("/user/**").hasRole("USER"));
        security.authorizeHttpRequests(authorize-> authorize.requestMatchers("/admin/**").hasRole("ADMIN"));
        security.exceptionHandling(authorise->authorise.accessDeniedPage("/errorPage"));
        security.authorizeHttpRequests(authorize-> authorize.anyRequest().authenticated());
        return security.build();
    }
}

