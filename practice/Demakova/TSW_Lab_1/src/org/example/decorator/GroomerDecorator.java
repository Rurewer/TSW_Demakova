package org.example.decorator;

import org.example.abstractfactory.Room;

public class GroomerDecorator extends RoomDecorator {
    public GroomerDecorator(Room room) {
        super(room);
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", с грумером";
    }

    @Override
    public double getCost() {
        return room.getCost() + 550.0;
    }
}
