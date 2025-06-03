package io.github.mitsumi.solutions.spring.json.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class User {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("birthday")
    private LocalDate birthday;

    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
}
