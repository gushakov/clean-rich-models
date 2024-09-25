package com.github.cleanrichmodels.core.model.customer;

import com.github.cleanrichmodels.core.model.Validate;
import lombok.Value;

@Value
public class Age implements Comparable<Age> {
    Integer years;

    public Age(Integer years) {
        this.years = Validate.strictlyPositive(years);
    }

    public boolean strictlyGreaterThan(Age age) {
        return compareTo(age) > 0;
    }

    @Override
    public int compareTo(Age another) {
        return this.years.compareTo(another.years);
    }
}