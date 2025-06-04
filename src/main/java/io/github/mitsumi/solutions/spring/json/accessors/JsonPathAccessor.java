package io.github.mitsumi.solutions.spring.json.accessors;

public interface JsonPathAccessor {

    /**
     * read value from jsonPath.
     *
     * @param jsonPath json path expression.
     * @return value.
     * @param <T> type of value.
     */
    <T> T read(String jsonPath);
}
