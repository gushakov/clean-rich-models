package com.github.cleanrichmodels.core.usecase.buyproduct;

public interface CustomerBuysProductInputPort {

    void customerBuysProduct(Long customerId, Long productId, Integer numberOfUnits);

}
