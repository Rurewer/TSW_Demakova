package org.example.server;

import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
    private final BlockingQueue<Order> requestQueue;
    private final int totalClients; //общее колво клиентов
    private int readyClients = 0; //счетчик

    public Server(BlockingQueue<Order> requestQueue, int totalClients) {
        this.requestQueue = requestQueue;
        this.totalClients = totalClients;
    }


    @Override
    public void run() {
        try {
            while (readyClients < totalClients) {
                Order clientOrder = requestQueue.take(); //извлекаем заказ из очереди
                double total = clientOrder.calculateTotal(); //вычисляем сумму заказа

                System.out.println("Сервер обработал заказ:");
                System.out.println(clientOrder);
                System.out.println("Итоговая сумма для " + clientOrder.getClientName() + ": " + total + " ₽\n");

                readyClients++; //увеличиваем счетчик обработ клиентов
            }
            System.out.println("Все запросы обработаны. Сервер завершает работу.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Сервер прерван.");
        }
    }

    public static void menu() {
        System.out.println("Добрый день! Внимание: в одном номере может содержаться одно животное.");
        System.out.println("Выберите номер (на 2 дня):");
        System.out.println("1. Обычный номер");
        System.out.println("   - Двухразовое питание (Сухой корм): 1200 ₽");
        System.out.println("   - Трехразовое питание (Сухой корм): 1300 ₽");
        System.out.println("   - Двухразовое питание (Влажный корм): 1250 ₽");
        System.out.println("   - Трехразовое питание (Влажный корм): 1400 ₽");
        System.out.println("2. Номер ЛЮКС");
        System.out.println("   - Двухразовое питание (Влажный корм): 1400 ₽");
        System.out.println("   - Трехразовое питание (Влажный корм): 1500 ₽");
        System.out.println("   - Двухразовое питание (Мясо): 1500 ₽");
        System.out.println("   - Трехразовое питание (Мясо): 1750 ₽");
        System.out.println("Дополнительные услуги:");
        System.out.println("   - Дополнительный день: 2000 ₽ за день");
        System.out.println("   - Грумер: 550 ₽");
        System.out.println("\n");
    }
}
