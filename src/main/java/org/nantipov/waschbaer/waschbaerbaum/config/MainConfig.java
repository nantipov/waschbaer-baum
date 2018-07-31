package org.nantipov.waschbaer.waschbaerbaum.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class MainConfig {

    @Bean
    public ObjectMapper getObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder
                .modulesToInstall(
                        new ParameterNamesModule()
                )
                .createXmlMapper(false)
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .build();
    }

}
