package io.github.mitsumi.solutions.spring.json.accessors;

public interface JsonPathAccessor {

    <T> T read(String jsonPath);
}
