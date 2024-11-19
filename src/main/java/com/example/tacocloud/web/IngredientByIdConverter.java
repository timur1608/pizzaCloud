package com.example.tacocloud.web;

import com.example.tacocloud.Ingredient;
import com.example.tacocloud.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    IngredientRepository inRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository inRepo) {
        this.inRepo = inRepo;
    }
    @Override
    public Ingredient convert(String source) {
        return inRepo.findById(source).orElse(null);
    }
}
