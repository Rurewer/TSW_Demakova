package org.example.abstractfactory;

public class DryFoodFactory implements RoomFactory {
    public Room createTwoEat() {
        return new ComTwoEat("Обычный номер: сухой корм", 1200.0);
    }

    public Room createSpecialEat() {
        return new ComThreeEat("Обычный номер: сухой корм", 1300.0);
    }
}
