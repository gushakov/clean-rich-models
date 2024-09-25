package com.github.cleanrichmodels.core.model.product;

import com.github.cleanrichmodels.core.model.Validate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductWithDayOfWeek {

    @EqualsAndHashCode.Include
    ProductId id;
    String name;
    BigDecimal unitBasePrice;

    @Getter(AccessLevel.NONE)
    Boolean discountApplicable;

    @Builder
    public ProductWithDayOfWeek(ProductId id, String name, BigDecimal unitBasePrice, Boolean discountApplicable) {
        this.id = Validate.notNull(id);
        this.name = Validate.notNull(name);
        this.unitBasePrice = Validate.strictlyPositive(unitBasePrice);
        this.discountApplicable = Validate.notNull(discountApplicable);
    }

    // system call is part of the logic for availability of the discount
    public Boolean getDiscountApplicable() {
        return discountApplicable && LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY;
    }

    // business methods omitted here
}
