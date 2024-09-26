## Use Cases and "Rich" Domain Models

Companion code for Medium article.

Here is the procedural code for the business scenario from the article, for reference.

```text
array<long, long, int, double> customer_buys_product(long customer_id, long product_id, int number_of_units){

    // get customer from the DB: ID, name, age
    array<long, string, int> customer = load_customer(customer_id);
    int customer_age = customer[2];

    // get product from the DB: ID, name, whether a discount applies
    array<long, string, bool, double> product = load_product(product_id);
    bool does_discount_apply = product[2];
    double unit_base_price = product[3];

    // get the day of the week from the system
    enum<MON,TUE,WEN,THU,FRI,SAT,SUN> day_of_week = system_day_of_week();

    /*
        Our business logic: calculate the total price for the order taking
        into the account a possible discount.
    */
    double totalPrice;
    if (does_discount_apply && customer_age > 45 && day_of_week == MON){
        totalPrice = (number_of_units * unit_base_price) / 2;
    }
    else {
        totalPrice = number_of_units * unit_base_price;
    }

    // save new order
    save_order(customer_id, product_id, number_of_units, totalPrice);

    // return information for the new order to the caller
    return array(customer_id, product_id, number_of_units, totalPrice);
}
```