package org.example.test;

import org.example.composite.MenuComponent;
import org.example.strategy.DefaultPriceStrategy;
import org.example.strategy.DiscountPriceStrategy;
import org.example.strategy.PriceStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceStrategyTest {

    @Test
    void defaultPriceStrategy_ShouldReturnOriginalCost() {
        // Arrange
        PriceStrategy strategy = new DefaultPriceStrategy();
        MenuComponent component = Mockito.mock(MenuComponent.class);
        when(component.getCost()).thenReturn(1000.0);

        // Act
        double result = strategy.calculateCost(component);

        // Assert
        assertEquals(1000.0, result);
    }

    @Test
    void discountPriceStrategy_ShouldApplyDiscount() {
        // Arrange
        PriceStrategy strategy = new DiscountPriceStrategy(25); // 25% discount
        MenuComponent component = Mockito.mock(MenuComponent.class);
        when(component.getCost()).thenReturn(1000.0);

        // Act
        double result = strategy.calculateCost(component);

        // Assert
        assertEquals(750.0, result);
    }
}