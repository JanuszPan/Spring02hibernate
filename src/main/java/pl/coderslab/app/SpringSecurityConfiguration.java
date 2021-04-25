package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.service.CustomUserDetailsService;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
@EnableJpaRepositories(basePackageClasses = {BookRepository.class})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("springHibernatePersistenceUnit");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager =
                new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//     return  NoOpPasswordEncoder.getInstance();//nie jest już wspierana
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("coderslab");
        dataSource.setUrl("jdbc:mysql://localhost:3306/onl_jee_w_02?serverTimezone=UTC");
        return dataSource;
    }

//    SpringSecurityConfiguration(final DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                //jest błąd w BCryptPasswordEncoder, trzeba zmienić $2y na $2a
//////                .withUser(User.builder().username("admin").password("$2a$12$JOg9QO7ZOwxjjoU2XzxiZuuKYi0K7asptCQ.ITNFOGmBWFsY9/y7K").authorities("ADMIN").build());
////                .withUser(User.builder().username("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN", "USER").build())
////                .withUser(User.builder().username("user").password(passwordEncoder().encode("user")).authorities("USER").build());
////    }
////        auth
////                .jdbcAuthentication()
////                .usersByUsernameQuery("select username,password,enabled from users where email=?")
//////                .withUser(User.builder().username("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN", "USER").build())
//////                .withUser(User.builder().username("user").password(passwordEncoder().encode("user")).authorities("USER").build())
////                .dataSource(dataSource());
//
//    }

    @Override //zabezpieczenie endpointów
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()// HttpSecurity, nie wyłącza się, bo zabezpiecza przed atakiem
                .exceptionHandling().accessDeniedPage("/auth/403page")
                .and()
                .authorizeRequests()
                .antMatchers("/form/book")
                .hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login")
                .and()
                .formLogin().loginPage("/auth/login").usernameParameter("email").successForwardUrl("/form/book")
                .permitAll();//strona logowania musi być dostępna dla wszystkich
    }
}