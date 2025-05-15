package org.example.abstractfactory;

public class WetFoodFactory implements RoomFactory {
    public Room createTwoEat() {
        return new ComTwoEat("Обычный номер: Влажный корм", 1250.0);
    }

    public Room createSpecialEat() {
        return new ComThreeEat("Обычный номер: Влажный корм", 1400.0);
    }
}
