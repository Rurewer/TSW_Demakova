package org.example.constants;

public enum FoodType {
    DRY_FOOD("Сухой корм"),
    WET_FOOD("Влажный корм"),
    LUX_WET_FOOD("Влажный корм ЛЮКС"),
    MEAT("Мясо");

    private final String description;

    FoodType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
