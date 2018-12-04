package com.vkonchuk.order.model;

public class Customer {

    private String name;
    private String surname;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String customerSurname) {
        this.surname = customerSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String customerEmail) {
        this.email = customerEmail;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer [name=").append(name)
               .append(", surname=").append(surname)
               .append(", email=").append(email)
               .append("]");
        return builder.toString();
    }

}
