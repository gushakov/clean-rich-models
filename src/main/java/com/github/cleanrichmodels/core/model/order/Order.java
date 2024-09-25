package com.github.cleanrichmodels.core.model.order;

import com.github.cleanrichmodels.core.model.product.ProductId;
import com.github.cleanrichmodels.core.model.Validate;
import com.github.cleanrichmodels.core.model.customer.CustomerId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @EqualsAndHashCode.Include
    OrderId id;
    CustomerId customerId;
    ProductId productId;
    Integer numberOfUnits;
    BigDecimal totalPrice;

    @Builder
    public Order(OrderId id, CustomerId customerId, ProductId productId, Integer numberOfUnits, BigDecimal totalPrice) {
        this.id = Validate.notNull(id);
        this.customerId = Validate.notNull(customerId);
        this.productId = Validate.notNull(productId);
        this.numberOfUnits = Validate.strictlyPositive(numberOfUnits);
        this.totalPrice = totalPrice;
    }

    // business methods omitted here
}
