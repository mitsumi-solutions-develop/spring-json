package io.github.mitsumi.solutions.spring.json;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.mitsumi.solutions.spring.json.accessors.JsonPathAccessorFactory;
import io.github.mitsumi.solutions.spring.json.test.models.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class JsonsTest {

    private final Jsons jsons = new Jsons();

    private final JsonPathAccessorFactory jsonPathAccessorFactory = new JsonPathAccessorFactory();


    @Test
    public void test_serialize_and_deserialize_success() {
        var user = user();
        var jsonString = jsons.serialize(user);
        var jsonPrettyString = jsons.serializePretty(user);

        assertSerialized(jsonString);
        assertSerialized(jsonPrettyString);

        var actualUser = jsons.deserialize(jsonString, User.class);

        assertDeserialized(actualUser, user);

        var actualUsers = jsons.deserialize(
            jsons.serialize(List.of(user)),
            new TypeReference<List<User>>() {}
        );

        assertThat(actualUsers.size(),  Matchers.is(1));
        assertDeserialized(actualUsers.getFirst(), user);
    }

    @Test
    public void test_deserialize_failure() {
        var actualThrows = Assertions.assertThrows(
            IllegalStateException.class,
            () -> jsons.deserialize("{\"name\":\"山田太郎\"\"age\":20}", User.class)
        );

        assertThat(actualThrows.getMessage(),  Matchers.is("Failed to deserialize."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"name\":\"山田太郎\", \"name_kana\":\"ヤマダ\", \"age\":20}",})
    public void test_deserialize_unknown_property(String jsonString) {
        var actual =  jsons.deserialize(jsonString, User.class);

        assertThat(actual.name(), Matchers.is("山田太郎"));
        assertThat(actual.age(), Matchers.is(20));
        assertThat(actual.address(), nullValue());
    }

    private void assertSerialized(String jsonString) {
        var actualJsonPathAccessor = jsonPathAccessorFactory.create(jsonString);

        assertThat(actualJsonPathAccessor.read("$.name"),  Matchers.is("山田 太郎"));
        assertThat(actualJsonPathAccessor.read("$.age"),  Matchers.is(20));
        assertThat(actualJsonPathAccessor.read("$.email"),  Matchers.is("yamada@taro.jp"));
        assertThat(actualJsonPathAccessor.read("$.phone"),  Matchers.is("080-1234-5678"));
        assertThat(actualJsonPathAccessor.read("$.address"),  Matchers.is("東京都千代田区"));
        assertThat(actualJsonPathAccessor.read("$.birthday"),  Matchers.is("1980-01-01"));
        assertThat(actualJsonPathAccessor.read("$.updated_at"),  Matchers.is("2020-01-01T12:00:00Z"));
    }

    private void assertDeserialized(User actualUser, User expectedUser) {
        assertThat(actualUser.name(),  Matchers.is(expectedUser.name()));
        assertThat(actualUser.age(),  Matchers.is(expectedUser.age()));
        assertThat(actualUser.email(),  Matchers.is(expectedUser.email()));
        assertThat(actualUser.phone(),  Matchers.is(expectedUser.phone()));
        assertThat(actualUser.address(),  Matchers.is(expectedUser.address()));
        assertThat(actualUser.birthday(),  Matchers.is(expectedUser.birthday()));
        assertThat(actualUser.updatedAt(),  Matchers.is(expectedUser.updatedAt()));
    }

    private User user() {
        return new User()
            .name("山田 太郎")
            .age(20)
            .email("yamada@taro.jp")
            .phone("080-1234-5678")
            .address("東京都千代田区")
            .birthday(LocalDate.of(1980, 1, 1))
            .updatedAt(OffsetDateTime.of(
                2020, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC
            ));
    }
}
