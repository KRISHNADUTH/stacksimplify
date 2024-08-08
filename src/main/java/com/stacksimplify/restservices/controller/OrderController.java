package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repo.OrderRepository;
import com.stacksimplify.restservices.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Get All Orders of a particular user
    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return userOptional.get().getOrders();
    }

    // Create order for a particular user.
    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        order.setUser(userOptional.get());
        return orderRepository.save(order);
    }

    // Get order by order ID
    @GetMapping("{userid}/orders/{orderid}")
    public Order getOrderById(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();
        List<Order> ordersOfUser = user.getOrders();
        for (Order order : ordersOfUser) {
            if (order.getOrderId() == orderid) {
                return order;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Order not found for the user with user id - " + userid);
    }
}
