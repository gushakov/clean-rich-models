package com.github.cleanrichmodels.core.port;

import com.github.cleanrichmodels.core.model.customer.Customer;
import com.github.cleanrichmodels.core.model.customer.CustomerId;
import com.github.cleanrichmodels.core.model.order.Order;
import com.github.cleanrichmodels.core.model.product.Product;
import com.github.cleanrichmodels.core.model.product.ProductId;

public interface PersistenceOperationsOutputPort {

    Customer obtainCustomerById(CustomerId customerId);

    Product obtainProductById(ProductId productId);

    void saveOrder(Order order);
}
