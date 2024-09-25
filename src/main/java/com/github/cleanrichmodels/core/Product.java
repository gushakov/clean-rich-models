package com.github.cleanrichmodels.core;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    ProductId id;
    String name;
    BigDecimal unitBasePrice;
    Boolean discountApplicable;

    @Builder
    public Product(ProductId id, String name, BigDecimal unitBasePrice, Boolean discountApplicable) {
        this.id = Validate.notNull(id);
        this.name = Validate.notNull(name);
        this.unitBasePrice = Validate.strictlyPositive(unitBasePrice);
        this.discountApplicable = Validate.notNull(discountApplicable);
    }

    // business methods omitted here
}
