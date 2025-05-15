package org.example.constants;

public enum MealPlan {
    TWO_MEALS("Двухразовое питание"),
    THREE_MEALS("Трехразовое питание");

    private final String description;

    MealPlan(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
