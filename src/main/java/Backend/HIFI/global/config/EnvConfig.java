package Backend.HIFI.global.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;

@Configuration
public class EnvConfig implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        final Environment environment = configurableListableBeanFactory.getBean(Environment.class);

        System.out.println("environment = " + environment);
    }

    @Override public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
