package org.example.test;

import org.example.abstractfactory.*;
import org.example.constants.FoodType;
import org.example.constants.MealPlan;
import org.example.constants.RoomType;
import org.example.server.Order;
import org.example.server.Request;
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

@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Mock
    private RoomFactoryProvider factoryProvider;

    @Mock
    private RoomFactory roomFactory;

    @Mock
    private Room room;

    @Mock
    private PriceStrategy priceStrategy;

    @InjectMocks
    private Order order;

    @Test
    void calculateTotal_ShouldReturnCorrectSum() {
        // Arrange
        Request request = new Request(RoomType.STANDARD, FoodType.DRY_FOOD, MealPlan.TWO_MEALS, 1, true);
        Order order = new Order("Кот Семён", List.of(request), "PROMO",
                priceStrategy, factoryProvider);

        // Создаем реальные декораторы вместо моков для полного тестирования
        Room baseRoom = new ComTwoEat("Обычный номер: сухой корм", 1200.0);
        when(factoryProvider.getFactory(FoodType.DRY_FOOD)).thenReturn(new DryFoodFactory());
        when(priceStrategy.calculateCost(any())).thenReturn(3750.0); // 1200 + 2000 (день) + 550 (грумер)

        // Act
        double total = order.calculateTotal();

        // Assert
        assertEquals(3750.0, total);
        verify(factoryProvider).getFactory(FoodType.DRY_FOOD);
        verify(priceStrategy).calculateCost(any());
    }

    @Test
    void createRoomFromRequest_ShouldCreateCorrectRoom() {
        // Arrange
        Order order = new Order("Test", List.of(), "", priceStrategy, factoryProvider);

        // Настроим моки для обоих методов
        Room twoEatRoom = mock(Room.class);
        Room threeEatRoom = mock(Room.class);

        when(roomFactory.createTwoEat()).thenReturn(twoEatRoom);
        when(roomFactory.createSpecialEat()).thenReturn(threeEatRoom);

        // Act & Assert for two meals
        Request twoMealsRequest = new Request(RoomType.STANDARD, FoodType.DRY_FOOD, MealPlan.TWO_MEALS, 0, false);
        Room result = order.createRoomFromRequest(roomFactory, twoMealsRequest);

        assertNotNull(result);
        assertEquals(twoEatRoom, result); // Проверяем, что вернулась правильная комната
        verify(roomFactory).createTwoEat();
        verify(roomFactory, never()).createSpecialEat(); // Убедимся, что не вызывался

        // Act & Assert for three meals
        Request threeMealsRequest = new Request(RoomType.STANDARD, FoodType.DRY_FOOD, MealPlan.THREE_MEALS, 0, false);
        result = order.createRoomFromRequest(roomFactory, threeMealsRequest);

        assertNotNull(result);
        assertEquals(threeEatRoom, result); // Проверяем, что вернулась правильная комната
        verify(roomFactory).createSpecialEat();
    }

    @Test
    void decorateRoom_ShouldAddDaysAndGroomer() {
        // Arrange
        Order order = new Order("Test", List.of(), "", priceStrategy, factoryProvider);
        when(room.getCost()).thenReturn(1000.0);

        // Act with days and groomer
        Request request = new Request(RoomType.STANDARD, FoodType.DRY_FOOD, MealPlan.TWO_MEALS, 2, true);
        Room result = order.decorateRoom(room, request);

        // Assert
        assertNotNull(result);
        // 1000 (база) + 2*2000 (дни) + 550 (грумер) = 5550
        assertEquals(5550.0, result.getCost());
    }
}