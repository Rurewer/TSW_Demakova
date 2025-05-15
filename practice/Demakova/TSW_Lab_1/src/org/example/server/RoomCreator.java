package org.example.server;

import org.example.abstractfactory.Room;
import org.example.abstractfactory.RoomFactory;
import org.example.builder.RoomBuilder;
import org.example.constants.MealPlan;
import org.example.constants.RoomType;

public class RoomCreator {
    public Room createRoom(RoomFactory factory, Request request) {
        Room room = request.getType().equals(RoomType.STANDARD)
                ? createStandardRoom(factory, request)
                : createLuxRoom(factory, request);

        return decorateRoom(room, request);
    }

    private Room createStandardRoom(RoomFactory factory, Request request) {
        return request.getVariant().equals(MealPlan.TWO_MEALS)
                ? factory.createTwoEat()
                : factory.createSpecialEat();
    }

    private Room createLuxRoom(RoomFactory factory, Request request) {
        return request.getVariant().equals(MealPlan.TWO_MEALS)
                ? factory.createTwoEat()
                : factory.createSpecialEat();
    }

    private Room decorateRoom(Room room, Request request) {
        RoomBuilder builder = new RoomBuilder(room);
        for (int i = 0; i < request.getDaysCount(); i++) {
            builder.addDays();
        }
        if (request.isWithGroomer()) {
            builder.addGroomer();
        }
        return builder.build();
    }
}