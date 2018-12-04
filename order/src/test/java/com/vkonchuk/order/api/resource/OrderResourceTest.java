package com.vkonchuk.order.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vkonchuk.order.model.Order;
import com.vkonchuk.order.model.OrderRequest;
import com.vkonchuk.order.service.OrderService;

public class OrderResourceTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderResource orderResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void statusCode200IsReturnedWhenOrderIsPlacedSuccessfullyTest() {
        when(orderService.placeOrder(any(OrderRequest.class))).thenReturn(new Order());
        ResponseEntity<Order> responseEntity = orderResource.placeOrder(new OrderRequest());
        assertThat(responseEntity.getStatusCode()).as("Status code must be 200 OK when order is placed successfully").isEqualTo(HttpStatus.OK);
    }

    @Test(expected = RuntimeException.class)
    public void runtimeExceptionIsThrownWhenRuntimeExceptionOccursDuringExecutionTest() {
        when(orderService.placeOrder(any(OrderRequest.class))).thenThrow(new RuntimeException("Test exception"));
        orderResource.placeOrder(new OrderRequest());
    }

}
