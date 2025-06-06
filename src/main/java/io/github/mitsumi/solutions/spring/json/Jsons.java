package io.github.mitsumi.solutions.spring.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mitsumi.solutions.spring.json.factories.ObjectMapperFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * ObjectMapperをラッパーしたクラス.
 *
 * @author mitsumi.kaneyama
 */
@RequiredArgsConstructor
public class Jsons {

    /**
     * ObjectMapper.
     */
    private final ObjectMapper objectMapper;

    /**
     * default constructor.
     */
    public Jsons() {
        this(ObjectMapperFactory.build().create());
    }

    /**
     * serialize an object to JSON string.
     *
     * @param value object to serialize.
     * @return json string.
     * @param <T> type of object.
     */
    public <T> String serialize(final T value) {
        return serialize(value, () -> new IllegalStateException("Failed to serialize."));
    }

    /**
     * serialize an object to JSON string.
     *
     * @param value object to serialize.
     * @param exceptionSupplier exception supplier.
     * @return json string.
     * @throws X exception.
     */
    public <T, X extends Throwable> String serialize(final T value,
                                                     final Supplier<X> exceptionSupplier) throws X {
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
    public <T> String serializePretty(final T value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw this.throwException(() -> new IllegalStateException("Failed to serialize."), e);
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
    public <T> T deserialize(final String content, final TypeReference<T> valueTypeRef) {
        return deserialize(content, valueTypeRef, () -> new IllegalStateException("Failed to deserialize."));
    }

    /**
     * deserialize a JSON string to an object.
     * @param content json string.
     * @param valueTypeRef type reference.
     * @param exceptionSupplier exception supplier.
     * @return deserialized object.
     * @throws X exception.
     */
    public <T, X extends Throwable> T deserialize(final String content,
                                                  final TypeReference<T> valueTypeRef,
                                                  final Supplier<X> exceptionSupplier) throws X {
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
    public <T> T deserialize(final String content, final Class<T> valueType) {
        return deserialize(content, valueType, () -> new IllegalStateException("Failed to deserialize."));
    }

    /**
     * deserialize a JSON string to an object.
     * @param content json string.
     * @param valueType type of object.
     * @param exceptionSupplier exception supplier.
     * @return deserialized object.
     * @throws X exception.
     */
    public <T, X extends Throwable> T deserialize(final String content,
                                                  final Class<T> valueType,
                                                  final Supplier<X> exceptionSupplier)
        throws X {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    /**
     * throw an exception.
     * @param exceptionSupplier exception supplier.
     * @param throwable throwable.
     * @return exception.
     * @throws X exception.
     */
    @SuppressWarnings("PMD.UseExplicitTypes")
    private <X extends Throwable> X throwException(final Supplier<X> exceptionSupplier,
                                                   final Throwable throwable) throws X {
        final var exception = exceptionSupplier.get();
        exception.addSuppressed(throwable);
        throw exception;
    }

}
