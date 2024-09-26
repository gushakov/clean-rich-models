package com.github.cleanrichmodels.core.usecase.buyproduct;

import com.github.cleanrichmodels.core.model.customer.Age;
import com.github.cleanrichmodels.core.model.customer.Customer;
import com.github.cleanrichmodels.core.model.customer.CustomerId;
import com.github.cleanrichmodels.core.model.order.Order;
import com.github.cleanrichmodels.core.model.order.OrderId;
import com.github.cleanrichmodels.core.model.product.Product;
import com.github.cleanrichmodels.core.model.product.ProductId;
import com.github.cleanrichmodels.core.port.DateTimeOperationsOutputPort;
import com.github.cleanrichmodels.core.port.IdGeneratorOperationsOutputPort;
import com.github.cleanrichmodels.core.port.PersistenceOperationsOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BuyProductUseCase implements BuyProductInputPort {

    BuyProductPresenterOutputPort presenter;
    PersistenceOperationsOutputPort persistenceOps;
    DateTimeOperationsOutputPort dateTimeOps;
    IdGeneratorOperationsOutputPort idGeneratorOps;

    @Override
    public void customerBuysProductWithDiscountIfApplicable(Long customerIdArg, Long productIdArg, Integer numberOfUnits) {
        // transaction demarcation and security checks are omitted for clarity

        try {
            // convert parameters into Value Objects as an equivalent of input validation
            CustomerId customerId = new CustomerId(customerIdArg);
            ProductId productId = new ProductId(productIdArg);

            // load aggregates involved in our scenario
            Customer customer = persistenceOps.obtainCustomerById(customerId);
            Product product = persistenceOps.obtainProductById(productId);

            // get the current day of the week by calling an output port (external service)
            DayOfWeek dayOfWeek = dateTimeOps.obtainCurrentDayOfWeek();

            // get the next available ID for the new order by calling an output port
            OrderId orderId = idGeneratorOps.obtainNextAvailableOrderId();

            // perform our business logic, as before, in "buy" method of "Customer"
            BigDecimal totalPrice;
            if (product.getDiscountApplicable()
                    && customer.getAge().strictlyGreaterThan(new Age(45))
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

            // create and persist new order
            Order order = Order.builder()
                    .id(orderId)
                    .customerId(customerId)
                    .productId(productId)
                    .numberOfUnits(numberOfUnits)
                    .totalPrice(totalPrice)
                    .build();
            persistenceOps.saveOrder(order);

            // present successful outcome to the user
            presenter.presentSuccessfulNewPurchaseOrderCreation(orderId, customer.getName(), product.getName(), numberOfUnits);

        } catch (Exception e) {
            // present error back to the user
            presenter.presentError(e);
        }

    }
}
