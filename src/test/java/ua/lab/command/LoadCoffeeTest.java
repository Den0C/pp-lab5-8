package ua.lab.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.good.CoffeeContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LoadCoffeeTest {
    private LoadCoffeeCommand loadCoffee;
    private CoffeeVan coffeeVan;
    private CoffeeContainer coffeeContainer;

    @BeforeEach
    void setUp() {
        loadCoffee = new LoadCoffeeCommand();
        coffeeVan = mock(CoffeeVan.class);
        coffeeContainer = mock(CoffeeContainer.class);
    }

    @Test
    void testConstructorWithCoffeeContainer() {
        LoadCoffeeCommand loadCoffeeWithContainer = new LoadCoffeeCommand(coffeeContainer);
        assertEquals(coffeeContainer, loadCoffeeWithContainer.getCoffeeContainer().orElse(null));
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "додати каву до вагону (load-coffee)";
        String info = loadCoffee.getInfo();

        assertEquals(expectedInfo, info);
    }

    @Test
    void testExecuteCoffeeContainer() {
        loadCoffee = new LoadCoffeeCommand(coffeeContainer);
        loadCoffee.execute(coffeeVan, Optional.empty());
        verify(coffeeVan).loadCoffee(eq(coffeeContainer));
    }

}