package org.example.composite;

import org.example.abstractfactory.Room;

public class MenuItem extends MenuComponent {
    private Room room;

    public MenuItem(Room room) {
        this.room = room;
    }

    @Override
    public String getDescription() {
        return room.getDescription();
    }

    @Override
    public double getCost() {
        return room.getCost();
    }

    @Override
    public void print() {
        System.out.println(getDescription() + " - " + getCost() + " ₽");
    }
}
