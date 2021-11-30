package com.target.assessment.myRetail.product.dto.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDescription {
    private String title;

    @JsonProperty("downstream_description")
    private String downstreamDescription;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownstreamDescription() {
        return downstreamDescription;
    }

    public void setDownstreamDescription(String downstreamDescription) {
        this.downstreamDescription = downstreamDescription;
    }
}
