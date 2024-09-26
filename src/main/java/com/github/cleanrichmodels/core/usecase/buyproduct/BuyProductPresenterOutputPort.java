package com.github.cleanrichmodels.core.usecase.buyproduct;

import com.github.cleanrichmodels.core.model.order.OrderId;

public interface BuyProductPresenterOutputPort {
    void presentError(Exception e);

    void presentSuccessfulNewPurchaseOrderCreation(OrderId orderId, String customerName, String productName, Integer numberOfUnits);
}
