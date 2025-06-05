package io.github.mitsumi.solutions.spring.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mitsumi.solutions.spring.json.Json;
import io.github.mitsumi.solutions.spring.json.accessors.JsonPathAccessorFactory;
import io.github.mitsumi.solutions.spring.json.factories.ObjectMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class JsonConfig {

    /**
     * create ObjectMapper.
     *
     * @return ObjectMapper.
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.build().create();
    }

    @Bean
    public JsonPathAccessorFactory jsonPathAccessorFactory() {
        return new JsonPathAccessorFactory();
    }

    @Bean
    public Json json() {
        return new Json();
    }
}
