package com.target.assessment.myRetail.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.common.collect.MinMaxPriorityQueue;
import com.target.assessment.myRetail.product.configs.MongoDbTestContainer;
import com.target.assessment.myRetail.product.configs.RedskyServerMocks;
import com.target.assessment.myRetail.product.configs.WireMockConfig;
import com.target.assessment.myRetail.product.data.repository.ProductRepository;
import com.target.assessment.myRetail.product.domain.ProductBo;
import com.target.assessment.myRetail.product.dto.Price;
import com.target.assessment.myRetail.product.dto.ProductResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@AutoConfigureMockMvc
@ContextConfiguration(classes = {WireMockConfig.class}, initializers = ProductApiIntegrationTests.MongoDbInitializer.class)
class ProductApiIntegrationTests {

    Logger log = Logger.getLogger("ProductApiIntegrationTests.class");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WireMockServer mockRedskyServer;


    private static MongoDbTestContainer mongoDbTestContainer;

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDbTestContainer = new MongoDbTestContainer();
        mongoDbTestContainer.start();
    }

    @Test
    public void testGetProductById() throws Exception {
        RedskyServerMocks.setupMockProductSuccessResponse(mockRedskyServer);
        ProductBo product = new ProductBo();
        product.setId("13860428");
        product.setCurrentPrice(Double.valueOf(100.0));
        product.setCurrencyCode("USD");
        productRepository.save(product);

        MvcResult result = mockMvc.perform(get("/api/v1/product/13860428")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        ProductResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ProductResponse.class);
        Assert.assertEquals("13860428", response.getId());
        Assert.assertEquals("The Big Lebowski (Blu-ray)", response.getName());
    }

    @Test
    public void testGetProductByIdAndProductNotFound() throws Exception {
        RedskyServerMocks.setupMockProductNotFoundResponse(mockRedskyServer);

        mockMvc.perform(get("/api/v1/product/13860429")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductByIdAndTooManyRequests() throws Exception {
        RedskyServerMocks.setupMockProductTooManyRequestResponse(mockRedskyServer);

        mockMvc.perform(get("/api/v1/product/13860428")
                        .contentType("application/json"))
                .andExpect(status().isFailedDependency());
    }

    @Test
    public void testGetProductByIdAndPriceDoesNotExists() throws Exception {
        RedskyServerMocks.setupMockProductSuccessResponse(mockRedskyServer);

        mockMvc.perform(get("/api/v1/product/13860428")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProductPrice() throws Exception {
        Price price = new Price.PriceBuilder().withValue(Double.valueOf(10.0)).withCurrencyCode("USD").build();
        mockMvc.perform(put("/api/v1/product/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(price)))
                .andExpect(status().isOk());
        Assert.assertNotNull(productRepository.findById("1"));
    }


    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        Logger log = Logger.getLogger("ProductApiIntegrationTests.MongoDbInitializer.class");

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.info("Overriding Spring Properties for mongodb !!!!!!!!!");

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbTestContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbTestContainer.getPort()

            );
            values.applyTo(configurableApplicationContext);
        }
    }
}