package org.example.abstractfactory;

public class LuxThreeEat extends Room {
    public LuxThreeEat(String eatType, double price) {
        description = eatType + ", трехразовое питание";
        cost = price;
    }
}
