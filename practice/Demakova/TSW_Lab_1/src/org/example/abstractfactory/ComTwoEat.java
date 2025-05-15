package org.example.abstractfactory;

public class ComTwoEat extends Room {
    public ComTwoEat(String eatType, double price) {
        description = eatType + ", двухразовое питание";
        cost = price;
    }
}
