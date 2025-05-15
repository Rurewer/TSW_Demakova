package org.example.server;//package org.example.server;
//
//import java.util.Arrays;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//
//public class Main {
//    public static void main(String[] args) {
//        //создаем очередь запросов
//        BlockingQueue<Order> requestQueue = new ArrayBlockingQueue<>(10);
//
//        //количество клиентов
//        int clientCount = 2;
//
//        //создаем и запускаем серверный поток
//        Thread serverThread = new Thread(new Server(requestQueue, clientCount));
//        serverThread.start();
//
//        //выводим меню
//        Server.menu();
//
//        //создаем потоки для клиентов
//        Thread client1 = new Thread(new Client(requestQueue,
//                new Order("Клиент 1", Arrays.asList(
//                        new Request("Обычный номер", "Сухой корм", "Двухразовое питание", 1, true),
//                        new Request("Обычный номер", "Влажный корм", "Трехразовое питание", 1, false)
//                ), "CAT2025")));
//
//        Thread client2 = new Thread(new Client(requestQueue,
//                new Order("Клиент 2", Arrays.asList(
//                        new Request("Номер ЛЮКС", "Мясо", "Двухразовое питание", 0, false),
//                        new Request("Номер ЛЮКС", "Влажный корм ЛЮКС", "Трехразовое питание", 0, true)
//                ), "")));
//
//        //запускаем клиентов
//        client1.start();
//        client2.start();
//
//
//        try {
//            //время должно быть достаточно для обработки всех запросов
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.out.println("Главный поток прерван.");
//        }
//        //останавливаем сервер
//
//
//        //ожидаем завершения потока сервера
//        try {
//            serverThread.join();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.out.println("Главный поток прерван при ожидании завершения сервера.");
//        }
//
//        System.out.println("Симуляция завершена.");
//    }
//}