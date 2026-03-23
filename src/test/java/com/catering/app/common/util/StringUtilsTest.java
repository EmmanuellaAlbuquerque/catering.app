package com.catering.app.common.util;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    void shouldSanitizeListRemovingNullBlankAndTrimmingValues() {
        Set<String> sanitizedValues = StringUtils.sanitizeList(Set.of("  email@teste.com  ", "valor", " "));

        assertThat(sanitizedValues)
                .containsExactlyInAnyOrder("email@teste.com", "valor");
    }

    @Test
    void shouldReturnEmptySetWhenSanitizingNullList() {
        Set<String> sanitizedValues = StringUtils.sanitizeList(null);

        assertThat(sanitizedValues).isEmpty();
    }

    @Test
    void shouldValidateOnlyNonBlankValues() {
        assertThat(StringUtils.isValid("conteudo")).isTrue();
        assertThat(StringUtils.isValid("   ")).isFalse();
        assertThat(StringUtils.isValid(null)).isFalse();
    }
}
