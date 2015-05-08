package demo;

import com.netflix.config.ConfigurationManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class RegistryServiceApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(RegistryServiceApplication.class, args);

        ConfigurationManager.getDeploymentContext().setDeploymentEnvironment(StringUtils.join(applicationContext.getEnvironment().getActiveProfiles(), ","));
        ConfigurationManager.getDeploymentContext().setDeploymentDatacenter("local");
    }
}
