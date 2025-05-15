package org.example.composite;

import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuComponent {
    private List<MenuComponent> menuComponents = new ArrayList<>();

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void print() {
        System.out.println("--------------------");
        for (MenuComponent menuComponent : menuComponents) {
            menuComponent.print();
        }
    }

    @Override
    public double getCost() {
        double totalCost = 0;
        for (MenuComponent menuComponent : menuComponents) {
            totalCost += menuComponent.getCost();
        }
        return totalCost;
    }
}
