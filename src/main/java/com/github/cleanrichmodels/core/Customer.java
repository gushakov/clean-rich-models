package com.github.cleanrichmodels.core;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @EqualsAndHashCode.Include
    CustomerId id;
    String name;
    Age age;

    @Builder
    Customer(CustomerId id, String name, Age age) {
        this.id = Validate.notNull(id);
        this.name = Validate.notBlank(name);
        this.age = Validate.notNull(age);
    }

    // avoiding "magic" constant
    private final static BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(2);

    /**
     * Customer buys specified {@code numberOfUnits} of a given {@code product}.
     * Returns a new {@linkplain Order} with total price of the order. A product
     * discount is applied if necessary.
     *
     * @param product       product to buy
     * @param numberOfUnits number of units of product to buy
     * @return new order
     */
    public Order buy(Product product, Integer numberOfUnits) {

        // get the current day of the week
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        // calculate total price by checking if discount applies
        BigDecimal totalPrice;
        if (product.getDiscountApplicable()
                && this.age.strictlyGreaterThan(new Age(45))
                && dayOfWeek == DayOfWeek.MONDAY) {
            // with the discount
            totalPrice = BigDecimal.valueOf(numberOfUnits)
                    .multiply(product.getUnitBasePrice())
                    .divide(DISCOUNT_RATE, RoundingMode.HALF_EVEN);
        } else {
            // without the discount
            totalPrice = BigDecimal.valueOf(numberOfUnits)
                    .multiply(product.getUnitBasePrice());
        }

        // make new order and return
        return Order.builder()
                .id(new OrderId(new Random().nextLong()))
                .customerId(this.id)
                .productId(product.getId())
                .numberOfUnits(numberOfUnits)
                .totalPrice(totalPrice)
                .build();
    }

}