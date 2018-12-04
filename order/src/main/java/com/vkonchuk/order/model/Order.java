package com.vkonchuk.order.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Customer customer;
    private List<Phone> items;
    private BigDecimal totalPrice;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Phone> getItems() {
        return items;
    }

    public void setItems(List<Phone> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [customer=").append(customer)
               .append(", items=").append(items)
               .append(", totalPrice=").append(totalPrice)
               .append("]");
        return builder.toString();
    }

}
