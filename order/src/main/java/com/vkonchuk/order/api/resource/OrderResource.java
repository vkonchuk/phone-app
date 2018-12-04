package com.vkonchuk.order.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vkonchuk.order.model.Order;
import com.vkonchuk.order.model.OrderRequest;
import com.vkonchuk.order.service.OrderService;

@Controller
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping("placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order placedOrder = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(placedOrder, HttpStatus.OK);
    }

}
