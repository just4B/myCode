
package configuration;

import com.ApplicationState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;



@Configuration
@ComponentScan("com")
public class Config {
   
    @Bean(name = "hibSession")
    public SessionFactory prepareDBConnection () {
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration()
                                                                      .configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                                                                       config.getProperties()
                                                                       ).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
    
    @Bean(name = "applicationState")
    @Scope("singleton")
    public ApplicationState createState () {
        return new ApplicationState();
    }   
}