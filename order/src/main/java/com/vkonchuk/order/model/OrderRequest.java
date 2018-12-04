package com.vkonchuk.order.model;

import java.util.List;

public class OrderRequest {

    private Customer customer;
    private List<Phone> items;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderRequest [customer=").append(customer)
               .append(", items=").append(items)
               .append("]");
        return builder.toString();
    }

}
