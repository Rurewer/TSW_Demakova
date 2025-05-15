package org.example.server;

import org.example.constants.RoomType;
import org.example.constants.FoodType;
import org.example.constants.MealPlan;

public class Request {
    private final RoomType type;
    private final FoodType eatType;
    private final MealPlan variant;
    private final int daysCount;
    private final boolean withGroomer;

    public Request(RoomType type, FoodType eatType, MealPlan variant,
                   int daysCount, boolean withGroomer) {
        if (type == null || eatType == null || variant == null) {
            throw new IllegalArgumentException("Тип номера, тип питания и вариант не могут быть null");
        }
        if (daysCount < 0) {
            throw new IllegalArgumentException("Количество дней не может быть отрицательным");
        }

        this.type = type;
        this.eatType = eatType;
        this.variant = variant;
        this.daysCount = daysCount;
        this.withGroomer = withGroomer;
    }

    // Геттеры
    public RoomType getType() {
        return type;
    }

    public FoodType getEatType() {
        return eatType;
    }

    public MealPlan getVariant() {
        return variant;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public boolean isWithGroomer() {
        return withGroomer;
    }

    @Override
    public String toString() {
        return type.getDescription() + " тип питания " + eatType.getDescription() +
                " (" + variant.getDescription() + "), доп. дни: " + daysCount +
                ", грумер: " + (withGroomer ? "да" : "нет");
    }
}