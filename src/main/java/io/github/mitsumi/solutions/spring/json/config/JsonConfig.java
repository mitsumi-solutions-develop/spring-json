package io.github.mitsumi.solutions.spring.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
