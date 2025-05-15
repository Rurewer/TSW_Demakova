package org.example.decorator;

import org.example.abstractfactory.Room;

public class DaysDecorator extends RoomDecorator {
    public DaysDecorator(Room room) {
        super(room);
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", с доп. днем";
    }

    @Override
    public double getCost() {
        return room.getCost() + 2000.0;
    }
}
