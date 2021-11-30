package com.target.assessment.myRetail.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {

    private String id;
    private String name;

    @JsonProperty("current_price")
    private Price currentPrice;

    public ProductResponse() {
    }

    public ProductResponse(ProductResponseBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.currentPrice = builder.currentPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductResponse that = (ProductResponse) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return currentPrice != null ? currentPrice.equals(that.currentPrice) : that.currentPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }

    public static class ProductResponseBuilder {

        private String id;
        private String name;
        private Price currentPrice;

        public ProductResponseBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ProductResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductResponseBuilder withPrice(Price currentPrice) {
            this.currentPrice = currentPrice;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(this);
        }

    }

}
