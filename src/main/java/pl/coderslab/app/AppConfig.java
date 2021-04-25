package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.coderslab.converter.StringToAuthorConverter;
import pl.coderslab.repository.BookRepository;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackageClasses = {BookDao.class, BookController.class})
//@ComponentScan(basePackages = "pl.coderslab.dao", "pl.coderslab.controller")
@ComponentScan(basePackages = "pl.coderslab")
@EnableJpaRepositories(basePackageClasses = BookRepository.class)// Spring Data - wystarczy jeden przedstawiciel z pakietu
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    @Override
    public  void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/auth/login");
        registry.addViewController("/auth/403page");
    }

//    @Bean
//    public LocalEntityManagerFactoryBean entityManagerFactory() {
//        LocalEntityManagerFactoryBean entityManagerFactoryBean
//                = new LocalEntityManagerFactoryBean();
//        entityManagerFactoryBean.setPersistenceUnitName("springHibernatePersistenceUnit");
//        return entityManagerFactoryBean;
//    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager =
                new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    //Dzięki zdefiniowaniu ViewResolver oraz prefiksu i sufiksu zamiast zwracać z kontrolera pełnej nazwy i ścieżki do widoku:
    //return "/WEB-INF/views/index.jsp"; będziemy mogli zwracać tylko jego nazwę:return "index";
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
        return localeResolver;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

   @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(stringToAuthorConverter());
    }
    @Bean
    public StringToAuthorConverter stringToAuthorConverter() {

        return new StringToAuthorConverter();
    }
    @Override//Kodowanie @ResponseBody
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain",
                Charset.forName("UTF-8"))));
        converters.add(stringConverter);
    }
    @Override//Bez tego ustawienia dla zasobów statycznych (obrazki, js, css) Spring próbowałby dopasować metodę kontrolera, która pasowałaby np. do adresu /logo.jpg
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}