package org.example.server;

import org.example.abstractfactory.Room;
import org.example.abstractfactory.RoomFactory;
import org.example.abstractfactory.RoomFactoryProvider;
import org.example.builder.RoomBuilder;
import org.example.constants.MealPlan;
import org.example.strategy.PriceStrategy;

import java.util.List;

public class Order {
    private final String clientName;
    private final List<Request> roomRequests;
    private final String promoCode;
    private final PriceStrategy priceStrategy;
    private final RoomFactoryProvider factoryProvider;

    public Order(String clientName, List<Request> roomRequests, String promoCode,
                 PriceStrategy priceStrategy, RoomFactoryProvider factoryProvider) {
        this.clientName = clientName;
        this.roomRequests = roomRequests;
        this.promoCode = promoCode;
        this.priceStrategy = priceStrategy;
        this.factoryProvider = factoryProvider;
    }

    public String getClientName() {
        return clientName;
    }

    public List<Request> getRoomRequests() {
        return roomRequests;
    }

    public String getPromoCode() {
        return promoCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ клиента ").append(clientName).append(":\n");
        for (Request request : roomRequests) {
            sb.append("  ").append(request).append("\n");
        }
        sb.append("Промокод: ").append(promoCode != null ? promoCode : "Нет\n");
        return sb.toString();
    }

    public double calculateTotal() {
        double total = 0;
        for (Request request : roomRequests) {
            RoomFactory factory = factoryProvider.getFactory(request.getEatType());
            Room room = createRoomFromRequest(factory, request);
            Room decoratedRoom = decorateRoom(room, request);
            total += priceStrategy.calculateCost(decoratedRoom);
        }
        return total;
    }

    public Room createRoomFromRequest(RoomFactory factory, Request request) {
        if (factory == null || request == null) {
            throw new IllegalArgumentException("Фабрика и запрос не могут быть null");
        }

        // Логика должна соответствовать тесту:
        // TWO_MEALS -> createTwoEat()
        // THREE_MEALS -> createSpecialEat()
        return request.getVariant() == MealPlan.TWO_MEALS
                ? factory.createTwoEat()
                : factory.createSpecialEat();
    }
    public Room decorateRoom(Room room, Request request) {
        if (room == null || request == null) {
            throw new IllegalArgumentException("Комната и запрос не могут быть null");
        }

        RoomBuilder builder = new RoomBuilder(room);

        if (request.getDaysCount() < 0) {
            throw new IllegalArgumentException("Количество дней не может быть отрицательным");
        }

        for (int i = 0; i < request.getDaysCount(); i++) {
            builder.addDays();
        }

        if (request.isWithGroomer()) {
            builder.addGroomer();
        }

        return builder.build();
    }
}

