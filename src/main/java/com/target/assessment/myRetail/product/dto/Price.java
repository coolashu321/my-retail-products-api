package com.target.assessment.myRetail.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

    private Double value;

    @JsonProperty("currency_code")
    private String currencyCode;

    public Price() {
    }

    public Price(PriceBuilder builder) {
        this.value = builder.value;
        this.currencyCode = builder.currencyCode;
    }

    public Double getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (value != null ? !value.equals(price.value) : price.value != null) return false;
        return currencyCode != null ? currencyCode.equals(price.currencyCode) : price.currencyCode == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        return result;
    }

    public static class PriceBuilder {
        private Double value;

        private String currencyCode;

        public PriceBuilder withValue(Double value) {
            this.value = value;
            return this;
        }

        public PriceBuilder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }
}
