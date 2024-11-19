package com.example.tacocloud.web;


import com.example.tacocloud.PizzaOrder;
import com.example.tacocloud.data.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
public class OrderController {
    OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid PizzaOrder pizzaOrder, Errors errors, SessionStatus sessionStatus){
        if( errors.hasErrors() ){
            return "orderForm";
        }
        log.info("Order submitted: {}", pizzaOrder);
        sessionStatus.setComplete();
        orderRepository.save(pizzaOrder);
        return "redirect:/";
    }
}
