package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
//     return  NoOpPasswordEncoder.getInstance();//nie jest już wspierana
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //jest błąd w BCryptPasswordEncoder, trzeba zmienić $2y na $2a
//                .withUser(User.builder().username("admin").password("$2a$12$JOg9QO7ZOwxjjoU2XzxiZuuKYi0K7asptCQ.ITNFOGmBWFsY9/y7K").authorities("ADMIN").build());
                .withUser(User.builder().username("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN", "USER").build())
                .withUser(User.builder().username("user").password(passwordEncoder().encode("user")).authorities("USER").build());
    }

    @Override //zabezpieczenie endpointów
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/auth/403page")
                .and()
                .authorizeRequests()
                .antMatchers("/form/book")
                .hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/auth/login")
                .permitAll();//strona logowania musi być dostępna dla wszystkich
    }
}