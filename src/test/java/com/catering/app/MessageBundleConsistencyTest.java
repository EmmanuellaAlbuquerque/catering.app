package com.catering.app;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MessageBundleConsistencyTest {

    @Test
    void shouldKeepAllMessageBundlesWithTheSameKeys() throws IOException {
        Set<String> portugueseKeys = loadProperties("messages_pt_BR.properties").stringPropertyNames();
        Set<String> englishKeys = loadProperties("messages_en.properties").stringPropertyNames();
        Set<String> spanishKeys = loadProperties("messages_es.properties").stringPropertyNames();

        assertThat(englishKeys).containsExactlyInAnyOrderElementsOf(portugueseKeys);
        assertThat(spanishKeys).containsExactlyInAnyOrderElementsOf(portugueseKeys);
    }

    private Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource(path);

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            properties.load(reader);
        }

        return properties;
    }
}
