package org.example.strategy;

import org.example.composite.MenuComponent;

public class DiscountPriceStrategy implements PriceStrategy {
    private double discountPercent;

    public DiscountPriceStrategy(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateCost(MenuComponent menuComponent) {
        double originalCost = menuComponent.getCost();
        return originalCost * (1 - discountPercent / 100);  // применяем скидку
    }
}
