package org.example.builder;

import org.example.abstractfactory.Room;
import org.example.decorator.DaysDecorator;
import org.example.decorator.GroomerDecorator;

public class RoomBuilder {
    private Room room;

    public RoomBuilder(Room room) {
        this.room = room;
    }

    public RoomBuilder addDays() {
        this.room = new DaysDecorator(room);
        return this;
    }

    public RoomBuilder addGroomer() {
        this.room = new GroomerDecorator(room);
        return this;
    }

    public Room build() {
        return room;
    }
}
