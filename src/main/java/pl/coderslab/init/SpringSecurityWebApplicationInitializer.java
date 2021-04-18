package pl.coderslab.init;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import pl.coderslab.app.SpringSecurityConfiguration;
public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public SpringSecurityWebApplicationInitializer() {
        super(SpringSecurityConfiguration.class);
    }
}
