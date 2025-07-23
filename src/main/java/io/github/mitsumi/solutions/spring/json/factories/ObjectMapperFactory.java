package io.github.mitsumi.solutions.spring.json.factories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;

/**
 *
 * @author mitsumi.kaneyama
 */
@NoArgsConstructor(staticName = "build")
@SuppressWarnings("PMD.CommentSize")
public class ObjectMapperFactory {

    /**
     * create ObjectMapper with default settings.
     *
     * <ul>
     *     <li>JsonInclude.Include.NON_NULL</li>
     *     <li>SerializationFeature.FAIL_ON_EMPTY_BEANS: false</li>
     *     <li>SerializationFeature.WRITE_DATES_AS_TIMESTAMPS: false</li>
     *     <li>DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES: false</li>
     *     <li>DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES: false</li>
     * </ul>
     * @return ObjectMapper.
     */
    public ObjectMapper create() {
        return new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());
    }
}
