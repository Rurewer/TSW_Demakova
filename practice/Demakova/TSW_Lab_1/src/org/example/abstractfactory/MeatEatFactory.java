package org.example.abstractfactory;

public class MeatEatFactory implements RoomFactory {
    @Override
    public Room createTwoEat() {
        return new LuxTwoEat("Номер ЛЮКС: мясо", 1500.0);
    }

    @Override
    public Room createSpecialEat() {
        return new LuxThreeEat("Номер ЛЮКС: мясо", 1600.0);
    }
}
