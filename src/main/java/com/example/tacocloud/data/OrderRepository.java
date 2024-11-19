package com.example.tacocloud.data;

import com.example.tacocloud.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
}
