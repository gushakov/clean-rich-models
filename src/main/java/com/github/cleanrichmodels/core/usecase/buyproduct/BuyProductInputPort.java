package com.github.cleanrichmodels.core.usecase.buyproduct;

import java.math.BigDecimal;

public interface BuyProductInputPort {

    BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(2);

    void customerBuysProductWithDiscountIfApplicable(Long customerIdArg, Long productIdArg, Integer numberOfUnits);

}
