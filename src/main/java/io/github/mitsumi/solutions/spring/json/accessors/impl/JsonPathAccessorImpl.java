package io.github.mitsumi.solutions.spring.json.accessors.impl;

import io.github.mitsumi.solutions.spring.json.accessors.JsonPathAccessor;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonPathAccessorImpl implements JsonPathAccessor {
    private final Object document;

    public <T> T read(String jsonPath) {
        return JsonPath.read(document, jsonPath);
    }
}
