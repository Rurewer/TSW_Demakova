package org.example.abstractfactory;

public class FeedEatFactory implements RoomFactory {

    @Override
    public Room createTwoEat() {
        return new LuxTwoEat("Номер ЛЮКС: влажный корм ЛЮКС", 1400.0);
    }

    @Override
    public Room createSpecialEat() {
        return new LuxThreeEat("Номер ЛЮКС: влажный корм ЛЮКС", 1500.0);
    }
}
