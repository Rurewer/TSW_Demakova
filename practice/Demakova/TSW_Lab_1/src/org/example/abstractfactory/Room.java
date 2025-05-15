package org.example.abstractfactory;

import org.example.composite.MenuComponent;

public abstract class Room extends MenuComponent {
    protected String description = " ";
    protected double cost = 0;

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}