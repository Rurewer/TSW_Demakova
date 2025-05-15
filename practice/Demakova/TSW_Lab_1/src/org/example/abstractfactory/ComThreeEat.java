package org.example.abstractfactory;

public class ComThreeEat extends Room {
    public ComThreeEat(String eatType, double price) {
        description = eatType + ", трехразовое питание";
        cost = price;
    }
}
