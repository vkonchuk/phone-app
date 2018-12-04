package com.vkonchuk.catalog.model;

import java.math.BigDecimal;

import com.vkonchuk.catalog.model.Phone;

public class Phone {

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image) {
        this.imageUrl = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Phone [name=").append(name)
               .append(", description=").append(description)
               .append(", imageUrl=").append(imageUrl)
               .append(", price=").append(price)
               .append("]");
        return builder.toString();
    }

}
