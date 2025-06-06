# spring-json

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) [![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

# supported

- java version: 21
# dependency

## maven

```xml
<dependency>
    <groupId>io.github.mitsumi-solutions-develop</groupId>
    <artifactId>spring-json</artifactId>
    <version>1.0.6</version>
</dependency>
```

## gradle

```
implementation group: 'io.github.mitsumi-solutions-develop', name: 'spring-json', version: '1.0.6'
```

# 背景

通常、`com.fasterxml.jackson.databind.ObjectMapper`を利用し、JSON `serialize`, `deserialize`を行うが、
`ObjectMapper`では、`java.io.IOException`が発生するため、
呼出元にて、場合によって、`try-catch`する必要がある.

上記を解消するために、ObjectMapperの一部処理をラッパーし、
メソッドにて、`try-catch`し、`RuntimeException`を`throw`するようにする.

# serialize

defaultでは、以下の通りに、serializeする.

- JsonInclude.Include.NON_NUL
- SerializationFeature.FAIL_ON_EMPTY_BEANS
- SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
