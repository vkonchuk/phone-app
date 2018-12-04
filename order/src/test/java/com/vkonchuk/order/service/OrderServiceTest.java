package com.vkonchuk.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vkonchuk.order.api.exception.InvalidOrderRequestException;
import com.vkonchuk.order.model.Customer;
import com.vkonchuk.order.model.Order;
import com.vkonchuk.order.model.OrderRequest;
import com.vkonchuk.order.model.Phone;

public class OrderServiceTest {

    private static final String ROOT_OF_THE_PATH = "/";
    private static final String PHONES_PATH = "/phones";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService.setCatalogServiceApiUrl(ROOT_OF_THE_PATH);
        OrderService.setRestTemplate(restTemplate);
    }

    @Test(expected = InvalidOrderRequestException.class)
    public void placeOrderWhenCustomerIsNullInvalidOrderRequestExceptionIsThrownTest() {
        orderService.placeOrder(getTestRequest(null, getTestPhones()));
    }

    @Test(expected = InvalidOrderRequestException.class)
    public void placeOrderWhenItemsAreNullInvalidOrderRequestExceptionIsThrownTest() {
        orderService.placeOrder(getTestRequest(getTestCustomer(), null));
    }

    @Test(expected = InvalidOrderRequestException.class)
    public void placeOrderWhenItemsAreEmptyInvalidOrderRequestExceptionIsThrownTest() {
        orderService.placeOrder(getTestRequest(getTestCustomer(), Collections.emptyList()));
    }

    @Test
    public void placeOrderWithCustomerAndValidItemsTest() {
        List<Phone> testPhones = getTestPhones();
        BigDecimal totalPriceOfTestPhones = testPhones.stream().map(Phone::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        ResponseEntity<List<Phone>> responseEntity = new ResponseEntity<List<Phone>>(testPhones, HttpStatus.OK);
        when(restTemplate.exchange(PHONES_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<List<Phone>>() {
        })).thenReturn(responseEntity);
        Order placedOrder = orderService.placeOrder(getTestRequest(getTestCustomer(), testPhones));
        assertThat(placedOrder.getTotalPrice()).as("Total price of the order should be equal to sum of the prices of all items")
                .isEqualTo(totalPriceOfTestPhones);
    }

    @Test(expected = InvalidOrderRequestException.class)
    public void placeOrderWithNotExistingPhoneTest() {
        List<Phone> testPhones = getTestPhones();
        ResponseEntity<List<Phone>> responseEntity = new ResponseEntity<List<Phone>>(testPhones, HttpStatus.OK);
        when(restTemplate.exchange(PHONES_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<List<Phone>>() {
        })).thenReturn(responseEntity);
        orderService.placeOrder(getTestRequest(getTestCustomer(), getNotExistingPhone()));
    }

    private OrderRequest getTestRequest(Customer customer, List<Phone> items) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomer(customer);
        orderRequest.setItems(items);
        return orderRequest;
    }

    private Customer getTestCustomer() {
        Customer customer = new Customer();
        customer.setName("TestName");
        customer.setSurname("TestSurname");
        customer.setEmail("TestEmail");
        return customer;
    }

    private List<Phone> getTestPhones() {
        List<Phone> phones = new ArrayList<>();
        phones.add(getPhone("Apple iPhone", "iPhone's description", "http://www.apple.com", "999.99"));
        phones.add(getPhone("Samsung Galaxy S9", "Galaxy's description", "https://www.samsung.com", "989.99"));
        phones.add(getPhone("Google Pixel 2", "Pixel's description", "https://www.google.com", "979.99"));
        return phones;
    }

    private Phone getPhone(String name, String description, String imageUrl, String price) {
        Phone phone = new Phone();
        phone.setName(name);
        phone.setDescription(description);
        phone.setImageUrl(imageUrl);
        phone.setPrice(new BigDecimal(price));
        return phone;
    }

    private List<Phone> getNotExistingPhone() {
        return Arrays.asList(getPhone("NonamePhone", "Fake description", "http://www.fake.com", "100.00"));
    }

}
