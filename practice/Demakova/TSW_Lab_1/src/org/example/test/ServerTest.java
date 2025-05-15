package org.example.test;

import org.example.server.Order;
import org.example.server.Server;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.BlockingQueue;


import static org.mockito.Mockito.*;

class ServerTest {

    @Test
    void run_ShouldProcessOrdersFromQueue() throws InterruptedException {
        // Arrange
        BlockingQueue<Order> queue = Mockito.mock(BlockingQueue.class);
        Order order = Mockito.mock(Order.class);
        when(queue.take()).thenReturn(order).thenThrow(new InterruptedException());
        when(order.calculateTotal()).thenReturn(1000.0);

        Server server = new Server(queue, 1);

        // Act
        server.run();

        // Assert
        verify(queue).take();
        verify(order).calculateTotal();
    }
}
