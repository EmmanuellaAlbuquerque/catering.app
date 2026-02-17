package com.catering.app.common.util;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {

    public static Set<String> sanitizeList(Set<String> list) {
        if (list == null) return Collections.emptySet();

        return list.stream()
                .filter(StringUtils::isValid)
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    public static boolean isValid(String value) {
        return value != null && !value.isBlank();
    }
}
