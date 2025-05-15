package org.example.abstractfactory;

public class LuxTwoEat extends Room {
    public LuxTwoEat(String eatType, double price) {
        description = eatType + ", двухразовое питание";
        cost = price;
    }
}
