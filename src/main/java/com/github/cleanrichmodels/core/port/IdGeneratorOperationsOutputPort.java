package com.github.cleanrichmodels.core.port;

import com.github.cleanrichmodels.core.model.order.OrderId;

public interface IdGeneratorOperationsOutputPort {

    OrderId obtainNextAvailableOrderId();

}
