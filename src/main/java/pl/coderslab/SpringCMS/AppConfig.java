package pl.coderslab.SpringCMS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.coderslab.SpringCMS.converter.AuthorConverter;
import pl.coderslab.SpringCMS.converter.CategoryConverter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan("pl.coderslab")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.SpringCMS.repository")
public class AppConfig {

    public Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new AuthorConverter());
        converters.add(new CategoryConverter());
        return converters;
    }
    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(getConverters());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
