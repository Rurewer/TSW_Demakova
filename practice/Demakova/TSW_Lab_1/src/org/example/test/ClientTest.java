package org.example.test;
import org.example.abstractfactory.*;
import org.example.server.Client;
import org.example.server.Order;
import org.example.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.concurrent.BlockingQueue;

class ClientTest {

    @Test
    void run_ShouldPutOrderToQueue() throws InterruptedException {
        // Arrange
        BlockingQueue<Order> queue = Mockito.mock(BlockingQueue.class);
        Order order = Mockito.mock(Order.class);
        Client client = new Client(queue, order);

        // Act
        client.run();

        // Assert
        verify(queue).put(order);
    }
}