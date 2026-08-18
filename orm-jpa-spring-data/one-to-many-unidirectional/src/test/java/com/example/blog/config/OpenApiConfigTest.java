package com.example.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenApiConfigTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    void exposesBlogApiMetadata() {
        assertThat(openAPI.getInfo()).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("Blog API");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("0.0.1-SNAPSHOT");
        assertThat(openAPI.getInfo().getDescription()).contains("OneToMany");
    }
}
