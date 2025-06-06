package io.github.mitsumi.solutions.spring.json.accessors.impl;

import io.github.mitsumi.solutions.spring.json.accessors.JsonPathAccessor;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@SuppressWarnings("PMD.CommentRequired")
public class JsonPathAccessorImpl implements JsonPathAccessor {

    private final Object document;

    /**
     * read value from jsonPath.
     *
     * @param jsonPath json path expression.
     * @return value.
     * @param <T> type of value.
     */
    @Override
    public <T> T read(final String jsonPath) {
        return JsonPath.read(document, jsonPath);
    }
}
