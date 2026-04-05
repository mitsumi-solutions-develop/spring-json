package io.github.mitsumi.solutions.spring.json.factories;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

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
     *     <li>JsonInclude.Include.NON_EMPTY</li>
     *     <li>SerializationFeature.FAIL_ON_EMPTY_BEANS: false</li>
     *     <li>SerializationFeature.WRITE_DATES_AS_TIMESTAMPS: false</li>
     *     <li>DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES: false</li>
     *     <li>DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES: false</li>
     * </ul>
     * @return ObjectMapper.
     */
    public ObjectMapper create() {
        return JsonMapper.builder()
            .changeDefaultPropertyInclusion(inclusion -> inclusion.withValueInclusion(JsonInclude.Include.NON_EMPTY))
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DateTimeFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();
    }
}
