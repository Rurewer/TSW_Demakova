package org.example.test;

import org.example.abstractfactory.Room;
import org.example.builder.RoomBuilder;
import org.example.decorator.DaysDecorator;
import org.example.decorator.GroomerDecorator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class RoomBuilderTest {

    @Test
    void build_ShouldReturnOriginalRoomWithoutDecorators() {
        // Arrange
        Room room = Mockito.mock(Room.class);
        RoomBuilder builder = new RoomBuilder(room);

        // Act
        Room result = builder.build();

        // Assert
        assertSame(room, result);
    }

    @Test
    void addDays_ShouldWrapRoomWithDaysDecorator() {
        // Arrange
        Room room = Mockito.mock(Room.class);
        when(room.getCost()).thenReturn(1000.0);
        RoomBuilder builder = new RoomBuilder(room);

        // Act
        builder.addDays();
        Room result = builder.build();

        // Assert
        assertTrue(result instanceof DaysDecorator);
        assertEquals(3000.0, result.getCost()); // 1000 + 2000
    }

    @Test
    void addGroomer_ShouldWrapRoomWithGroomerDecorator() {
        // Arrange
        Room room = Mockito.mock(Room.class);
        when(room.getCost()).thenReturn(1000.0);
        RoomBuilder builder = new RoomBuilder(room);

        // Act
        builder.addGroomer();
        Room result = builder.build();

        // Assert
        assertTrue(result instanceof GroomerDecorator);
        assertEquals(1550.0, result.getCost()); // 1000 + 550
    }
}