package com.target.assessment.myRetail.product.configs;

import org.testcontainers.containers.GenericContainer;

public class MongoDbTestContainer extends GenericContainer<MongoDbTestContainer> {

    public static final int MONGODB_PORT = 27017;
    public static final String DEFAULT_IMAGE_AND_TAG = "mongo:3.2.4";
    public MongoDbTestContainer() {
        this(DEFAULT_IMAGE_AND_TAG);
    }
    public MongoDbTestContainer(String image) {
        super(image);
        addExposedPort(MONGODB_PORT);
    }

    public Integer getPort() {
        return getMappedPort(MONGODB_PORT);
    }

}