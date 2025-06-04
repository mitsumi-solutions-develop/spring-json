package io.github.mitsumi.solutions.spring.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mitsumi.solutions.spring.json.factories.ObjectMapperFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

@Component
public class Json {

    private final ObjectMapper objectMapper;

    public Json() {
        this(ObjectMapperFactory.build().create());
    }

    public Json(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * serialize an object to JSON string.
     *
     * @param value object to serialize.
     * @return json string.
     * @param <T> type of object.
     */
    public <T> String serialize(T value) {
        return serialize(value, () -> new RuntimeException("Failed to serialize."));
    }

    /**
     * serialize an object to JSON string.
     *
     * @param value object to serialize.
     * @param exceptionSupplier exception supplier.
     * @return json string.
     * @param <T> type of object.
     * @param <X> type of exception.
     * @throws X exception.
     */
    public <T, X extends Throwable> String serialize(T value, Supplier<X> exceptionSupplier) throws X {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    /**
     * serialize an object to pretty JSON string.
     * @param value object to serialize.
     * @return json string.
     * @param <T> type of object.
     */
    public <T> String serializePretty(T value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw this.throwException(() -> new RuntimeException("Failed to serialize."), e);
        }
    }

    /**
     * deserialize a JSON string to an object.
     *
     * @param content json string.
     * @param valueTypeRef type reference.
     * @return deserialized object.
     * @param <T> type of object.
     */
    public <T> T deserialize(String content, TypeReference<T> valueTypeRef) {
        return deserialize(content, valueTypeRef, () -> new RuntimeException("Failed to deserialize."));
    }

    /**
     * deserialize a JSON string to an object.
     *
     * @param content json string.
     * @param valueTypeRef type reference.
     * @param exceptionSupplier exception supplier.
     * @return deserialized object.
     * @param <T> type of object.
     * @param <X> type of exception.
     * @throws X exception.
     */
    public <T, X extends Throwable> T deserialize(String content, TypeReference<T> valueTypeRef,
                                                  Supplier<X> exceptionSupplier) throws X {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    /**
     * deserialize a JSON string to an object.
     *
     * @param content json string.
     * @param valueType type.
     * @return deserialized object.
     * @param <T> type of object.
     */
    public <T> T deserialize(String content, Class<T> valueType) {
        return deserialize(content, valueType, () -> new RuntimeException("Failed to deserialize."));
    }

    /**
     * deserialize a JSON string to an object.
     * @param content json string.
     * @param valueType type of object.
     * @param exceptionSupplier exception supplier.
     * @return deserialized object.
     * @param <T> type of object.
     * @param <X> type of exception.
     * @throws X exception.
     */
    public <T, X extends Throwable> T deserialize(String content, Class<T> valueType, Supplier<X> exceptionSupplier)
        throws X {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    /**
     * throw an exception.
     *
     * @param exceptionSupplier exception supplier.
     * @param throwable throwable.
     * @return exception.
     * @param <X> type of exception.
     * @throws X exception.
     */
    private <X extends Throwable> X throwException(Supplier<X> exceptionSupplier, Throwable throwable) throws X {
        var exception = exceptionSupplier.get();
        exception.addSuppressed(throwable);
        throw exception;
    }

}
