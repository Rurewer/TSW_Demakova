package org.example.server;

import org.example.abstractfactory.DefaultRoomFactoryProvider;
import org.example.abstractfactory.RoomFactoryProvider;
import org.example.constants.FoodType;
import org.example.constants.MealPlan;
import org.example.constants.RoomType;
import org.example.strategy.DefaultPriceStrategy;
import org.example.strategy.DiscountPriceStrategy;
import org.example.strategy.PriceStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main2 {
    public static void main(String[] args) {
        BlockingQueue<Order> requestQueue = new ArrayBlockingQueue<>(5);
        int numberOfClients = 3;

        Server.menu();

        // Создаем стратегии и провайдеры
        PriceStrategy defaultStrategy = new DefaultPriceStrategy();
        PriceStrategy discountStrategy = new DiscountPriceStrategy(25);
        RoomFactoryProvider factoryProvider = new DefaultRoomFactoryProvider();

        // Создаем заказы
        List<Order> orders = Arrays.asList(
                new Order("Кот Семён и Кошка Мира", Arrays.asList(
                        new Request(RoomType.STANDARD, FoodType.DRY_FOOD, MealPlan.TWO_MEALS, 1, true),
                        new Request(RoomType.STANDARD, FoodType.WET_FOOD, MealPlan.THREE_MEALS, 1, false)
                ), "CAT2025", discountStrategy, factoryProvider),

                new Order("Кот Пушок и Кот Борис", Arrays.asList(
                        new Request(RoomType.LUX, FoodType.MEAT, MealPlan.TWO_MEALS, 0, false),
                        new Request(RoomType.LUX, FoodType.LUX_WET_FOOD, MealPlan.THREE_MEALS, 0, true)
                ), "", defaultStrategy, factoryProvider),

                new Order("Кот Степан", Arrays.asList(
                        new Request(RoomType.LUX, FoodType.MEAT, MealPlan.THREE_MEALS, 3, false)
                ), "", defaultStrategy, factoryProvider)
        );

        //запускаем сервер в отдельном потоке
        Thread serverThread = new Thread(new Server(requestQueue, numberOfClients));
        serverThread.start();

        //запускаем клиентов
        Thread[] clientThreads = new Thread[numberOfClients];

        for (int i = 0; i < numberOfClients; i++) {
            Order order = orders.get(i); //получаем заказ для клиента
            Client client = new Client(requestQueue, order); //создаем клиента с заказом

            //создаем и запускаем поток для клиента
            clientThreads[i] = new Thread(client, "Клиент-" + (i + 1) + " Thread");
            clientThreads[i].start();

        }

        //ожидаем завершения всех клиентских потоков
        try {
            for (Thread clientThread : clientThreads) {
                clientThread.join();  //ожидаем завершения каждого клиента
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Главный поток прерван.");
        }

        //останавливаем сервер
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Главный поток прерван при ожидании завершения сервера.");
        }

        System.out.println("Симуляция завершена.");
    }
}