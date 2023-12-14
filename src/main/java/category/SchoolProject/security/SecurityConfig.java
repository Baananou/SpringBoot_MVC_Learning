package category.SchoolProject.security;

import category.SchoolProject.security.service.IServiceAccount;
import category.SchoolProject.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    UserDetailsServiceImpl userDetailsService;
    PasswordEncoder passwordEncoder ;
//   @Bean
    CommandLineRunner commandLineRunner2(IServiceAccount serviceAccount){
        return args -> {
            serviceAccount.addRole("ADMIN");
            serviceAccount.addRole("USER");
            serviceAccount.addUser("user","1234","user1234@gmail.com");
            serviceAccount.addUser("user1","11234","user11234@gmail.com");
            serviceAccount.addUser("admin","1234","admin@gmail.com");
            serviceAccount.addRoleToUser("user","USER");
            serviceAccount.addRoleToUser("user1","USER");
            serviceAccount.addRoleToUser("admin","ADMIN");
            serviceAccount.addRoleToUser("admin","USER");
        };
    }

   // @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build()
                );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.httpBasic(Customizer.withDefaults());
        security.formLogin(form->form.permitAll());
        security.authorizeHttpRequests(authorize-> authorize.requestMatchers("/user/**").hasAuthority("USER"));
        security.authorizeHttpRequests(authorize-> authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        security.authorizeHttpRequests(authorize-> authorize.anyRequest().authenticated());
        security.exceptionHandling(authorise->authorise.accessDeniedPage("/errorPage"));
        security.userDetailsService(userDetailsService);
        security.csrf(c->c.disable());
        return security.build();
    }
}