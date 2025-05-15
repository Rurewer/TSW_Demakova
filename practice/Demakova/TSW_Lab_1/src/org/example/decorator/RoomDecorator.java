package org.example.decorator;

import org.example.abstractfactory.Room;

abstract class RoomDecorator extends Room {
    protected Room room;

    public RoomDecorator(Room room) {
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
}
