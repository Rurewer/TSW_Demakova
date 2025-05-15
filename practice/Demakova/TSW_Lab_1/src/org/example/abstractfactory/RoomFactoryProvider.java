package org.example.abstractfactory;

import org.example.constants.FoodType;

public interface RoomFactoryProvider {
    RoomFactory getFactory(FoodType foodType);
}
