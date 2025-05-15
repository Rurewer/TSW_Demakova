package org.example.strategy;

import org.example.composite.MenuComponent;

public class DefaultPriceStrategy implements PriceStrategy {
    @Override
    public double calculateCost(MenuComponent menuComponent) {
        return menuComponent.getCost();
    }
}
