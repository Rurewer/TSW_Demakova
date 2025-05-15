package org.example.abstractfactory;

import org.example.constants.FoodType;

public class DefaultRoomFactoryProvider implements RoomFactoryProvider {
    @Override
    public RoomFactory getFactory(FoodType foodType) {
        if (foodType == null) {
            throw new IllegalArgumentException("Тип питания не может быть null");
        }

        switch (foodType) {
            case DRY_FOOD:
                return new DryFoodFactory();
            case WET_FOOD:
                return new WetFoodFactory();
            case MEAT:
                return new MeatEatFactory();
            case LUX_WET_FOOD:
                return new FeedEatFactory();
            default:
                throw new IllegalArgumentException("Неизвестный тип питания: " + foodType);
        }
    }
}