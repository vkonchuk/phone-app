package com.vkonchuk.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vkonchuk.order.api.exception.InvalidOrderRequestException;
import com.vkonchuk.order.model.Order;
import com.vkonchuk.order.model.OrderRequest;
import com.vkonchuk.order.model.Phone;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    //private static final RestTemplate restTemplate = new RestTemplate();
    private static RestTemplate restTemplate = new RestTemplate();

    private static final String PHONES_PATH = "phones";

    @Value("${catalog.service.api.url}")
    private String catalogServiceApiUrl;

    public Order placeOrder(OrderRequest orderRequest) {
        validateOrder(orderRequest);
        Order placedOrder = createOrderFromOrderRequest(orderRequest);
        BigDecimal totalPrice = calculateTotalPrice(placedOrder);
        placedOrder.setTotalPrice(totalPrice);
        logger.info("New order was placed successfully: {}", placedOrder);
        return placedOrder;
    }

    private void validateOrder(OrderRequest orderRequest) {
        if (orderRequest.getCustomer() == null) {
            logErrorAndThrowException("Customer is not available in the input request");
        }
        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            logErrorAndThrowException("There are no items available for purchase in the input request");
        }
        String requestUrl = catalogServiceApiUrl + PHONES_PATH;
        ResponseEntity<List<Phone>> catalogResponse = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Phone>>() {});
        List<Phone> allPhones = catalogResponse.getBody();
        List<Phone> unknownItems = orderRequest.getItems().stream()
                                                          .filter(phone -> !allPhones.contains(phone))
                                                          .collect(Collectors.toList());
        if (!unknownItems.isEmpty()) {
            logErrorAndThrowException("Following phones requested for purchase are not available in our store: " + unknownItems.toString());
        }
    }

    private void logErrorAndThrowException(String errorMessage) {
        logger.error(errorMessage);
        throw new InvalidOrderRequestException(errorMessage);
    }

    private Order createOrderFromOrderRequest(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomer(orderRequest.getCustomer());
        order.setItems(orderRequest.getItems());
        return order;
    }

    private BigDecimal calculateTotalPrice(Order order) {
        return order.getItems().stream()
                               .map(Phone::getPrice)
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // used only for tests
    protected void setCatalogServiceApiUrl(String catalogServiceApiUrl) {
        this.catalogServiceApiUrl = catalogServiceApiUrl;
    }

    protected static void setRestTemplate(RestTemplate restTemplate) {
        OrderService.restTemplate = restTemplate;
    }

}
