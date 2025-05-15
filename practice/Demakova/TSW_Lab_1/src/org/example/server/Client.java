package org.example.server;

import java.util.concurrent.BlockingQueue;

public class Client implements Runnable {
    private final BlockingQueue<Order> requestQueue;
    private final Order clientOrder;

    public Client(BlockingQueue<Order> requestQueue, Order clientOrder) {
        this.requestQueue = requestQueue;
        this.clientOrder = clientOrder;
    }

    @Override
    public void run() {
        try {
            System.out.println("Питомец " + clientOrder.getClientName()+ " запущен и начинает отправку запросов.");
            requestQueue.put(clientOrder); //передаем заказ клиента в очередь
            System.out.println("Питомец " + clientOrder.getClientName() + " забронировал номер.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Питомец прерван.");
        }
    }
}
