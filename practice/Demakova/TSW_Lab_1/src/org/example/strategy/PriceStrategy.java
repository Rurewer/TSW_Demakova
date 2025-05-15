package org.example.strategy;

import org.example.composite.MenuComponent;

public interface PriceStrategy {
    double calculateCost(MenuComponent menuComponent);
}
