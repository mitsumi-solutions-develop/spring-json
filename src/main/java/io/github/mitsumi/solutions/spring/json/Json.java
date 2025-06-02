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

    public <T> String serialize(T value) {
        return serialize(value, () -> new RuntimeException("Failed to serialize."));
    }

    public <T, X extends Throwable> String serialize(T value, Supplier<X> exceptionSupplier) throws X {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    public <T> String serializePretty(T value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw this.throwException(() -> new RuntimeException("Failed to serialize."), e);
        }
    }

    public <T> T deserialize(String content, TypeReference<T> valueTypeRef) {
        return deserialize(content, valueTypeRef, () -> new RuntimeException("Failed to deserialize."));
    }

    public <T, X extends Throwable> T deserialize(String content, TypeReference<T> valueTypeRef,
                                                  Supplier<X> exceptionSupplier) throws X {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    public <T> T deserialize(String content, Class<T> valueType) {
        return deserialize(content, valueType, () -> new RuntimeException("Failed to deserialize."));
    }

    public <T, X extends Throwable> T deserialize(String content, Class<T> valueType, Supplier<X> exceptionSupplier)
        throws X {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw this.throwException(exceptionSupplier, e);
        }
    }

    private <X extends Throwable> X throwException(Supplier<X> exceptionSupplier, Throwable throwable) throws X {
        var exception = exceptionSupplier.get();
        exception.addSuppressed(throwable);
        throw exception;
    }

}
