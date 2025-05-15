package org.example.test;
import org.example.abstractfactory.*;
import org.example.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class RoomFactoryTest {

    @Test
    void dryFoodFactory_ShouldCreateCorrectRooms() {
        // Arrange
        RoomFactory factory = new DryFoodFactory();

        // Act
        Room twoEat = factory.createTwoEat();
        Room threeEat = factory.createSpecialEat();

        // Assert
        assertEquals("Обычный номер: сухой корм, двухразовое питание", twoEat.getDescription());
        assertEquals(1200.0, twoEat.getCost());
        assertEquals("Обычный номер: сухой корм, трехразовое питание", threeEat.getDescription());
        assertEquals(1300.0, threeEat.getCost());
    }

    @Test
    void wetFoodFactory_ShouldCreateCorrectRooms() {
        // Arrange
        RoomFactory factory = new WetFoodFactory();

        // Act
        Room twoEat = factory.createTwoEat();
        Room threeEat = factory.createSpecialEat();

        // Assert
        assertEquals("Обычный номер: Влажный корм, двухразовое питание", twoEat.getDescription());
        assertEquals(1250.0, twoEat.getCost());
        assertEquals("Обычный номер: Влажный корм, трехразовое питание", threeEat.getDescription());
        assertEquals(1400.0, threeEat.getCost());
    }
}