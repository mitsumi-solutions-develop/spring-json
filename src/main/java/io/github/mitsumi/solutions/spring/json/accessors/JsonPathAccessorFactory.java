package io.github.mitsumi.solutions.spring.json.accessors;

import io.github.mitsumi.solutions.spring.json.accessors.impl.JsonPathAccessorImpl;
import com.jayway.jsonpath.Configuration;
import org.springframework.stereotype.Component;

@Component
public class JsonPathAccessorFactory {

    /**
     * create JsonPathAccessor by specified json string.
     * @param json json string.
     * @return JsonPathAccessor.
     */
    public JsonPathAccessor create(String json) {
        var provider = Configuration.defaultConfiguration().jsonProvider();

        return new JsonPathAccessorImpl(provider.parse(json));
    }
}
