package com.example.tacocloud.web;


import com.example.tacocloud.Ingredient;
import com.example.tacocloud.Pizza;
import com.example.tacocloud.PizzaOrder;
import com.example.tacocloud.data.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.tacocloud.Ingredient.Type;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
    IngredientRepository inRepo;

    @Autowired
    public DesignPizzaController(IngredientRepository inRepo) {
        this.inRepo = inRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = inRepo.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder Order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processPizza(@Valid Pizza newPizza, Errors errors, @ModelAttribute PizzaOrder pizzaOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        pizzaOrder.AddPizza(newPizza);
        log.info("Processing pizza " + newPizza);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
//        log.info(ingredients.toString());
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
