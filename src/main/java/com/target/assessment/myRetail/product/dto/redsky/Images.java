package com.target.assessment.myRetail.product.dto.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
    @JsonProperty("primary_image_url")
    String primaryImageUrl;

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }
}
