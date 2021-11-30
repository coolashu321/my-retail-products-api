package com.target.assessment.myRetail.product.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class for Product Document of MongoDB.
 *
 * @author ashutosh gupta
 */
@Document("product")
public class ProductBo {

    /**
     * Product Id
     */
    @Id
    private String id;

    /**
     * current price of product.
     */
    private Double currentPrice;

    /**
     * currency code for current price of product.
     */
    private String currencyCode;

    /**
     * Below are Getter/Setter methods of attributes of this class.
     */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBo productBo = (ProductBo) o;

        if (!id.equals(productBo.id)) return false;
        if (!currentPrice.equals(productBo.currentPrice)) return false;
        return currencyCode.equals(productBo.currencyCode);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + currentPrice.hashCode();
        result = 31 * result + currencyCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductBo{" +
                "id='" + id + '\'' +
                ", currentPrice=" + currentPrice +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
