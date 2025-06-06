package io.github.mitsumi.solutions.spring.json.accessors;

import io.github.mitsumi.solutions.spring.json.accessors.impl.JsonPathAccessorImpl;
import com.jayway.jsonpath.Configuration;
import lombok.NoArgsConstructor;

/**
 * JsonPathAccessorFactory.
 *
 * @author mitsumi.kaneyama
 */
@NoArgsConstructor
public class JsonPathAccessorFactory {

    /**
     * create JsonPathAccessor by specified json string.
     * @param json json string.
     * @return JsonPathAccessor.
     */
    @SuppressWarnings("PMD.UseExplicitTypes")
    public JsonPathAccessor create(final String json) {
        final var provider = Configuration.defaultConfiguration().jsonProvider();

        return new JsonPathAccessorImpl(provider.parse(json));
    }
}
