package com.example.tacocloud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        CHEESE, SAUSAGE, MAYONNAISE, SALAD, TOMATO, BEACON, WRAP, PROTEIN, VEGGIES, SAUCE
    }
}
