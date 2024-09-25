package com.github.cleanrichmodels.core;

import java.math.BigDecimal;
import java.util.Optional;

public class Validate {
    static Integer strictlyPositive(Integer value) {
        return Optional.ofNullable(value)
                .filter(val -> val > 0)
                .orElseThrow(InvalidDomainObjectError::new);
    }

    static BigDecimal strictlyPositive(BigDecimal value) {
        return Optional.ofNullable(value)
                .filter(val -> val.compareTo(BigDecimal.ZERO) > 0)
                .orElseThrow(InvalidDomainObjectError::new);
    }

    static <T> T notNull(T value) {
        return Optional.ofNullable(value).orElseThrow(InvalidDomainObjectError::new);
    }

    static String notBlank(String text) {
        return Optional.ofNullable(text)
                .filter(t -> !t.isBlank())
                .orElseThrow(InvalidDomainObjectError::new);
    }
}