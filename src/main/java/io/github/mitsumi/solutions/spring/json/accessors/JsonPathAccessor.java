package io.github.mitsumi.solutions.spring.json.accessors;

/**
 * Read the specified JSON path.
 *
 * @author mitsumi.kaneyama
 */
@FunctionalInterface
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
