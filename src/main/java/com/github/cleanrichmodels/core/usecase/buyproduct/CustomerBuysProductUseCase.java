package com.github.cleanrichmodels.core.usecase.buyproduct;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomerBuysProductUseCase implements CustomerBuysProductInputPort {

    CustomerBuysProductPresenterOutputPort presenter;

    @Override
    public void customerBuysProduct(Long customerId, Long productId, Integer numberOfUnits) {

    }
}
