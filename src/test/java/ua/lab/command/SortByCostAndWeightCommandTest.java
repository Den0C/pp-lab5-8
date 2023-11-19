package ua.lab.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lab.coffeeCar.CoffeeVan;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SortByCostAndWeightCommandTest {
    private SortByCostAndWeightCommand sortByCostAndWeightCommand;
    private CoffeeVan coffeeVan;

    @BeforeEach
    void setUp() {
        sortByCostAndWeightCommand = new SortByCostAndWeightCommand();
        coffeeVan = mock(CoffeeVan.class);
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "посортувати (sort-data)";
        String info = sortByCostAndWeightCommand.getInfo();
        assertEquals(expectedInfo, info);
    }

    @Test
    void testExecute() {
        Optional<String> additionalCommand = Optional.of("command");
        sortByCostAndWeightCommand.execute(coffeeVan, additionalCommand);
        verify(coffeeVan).sortByCostAndWeight();
        verifyNoMoreInteractions(coffeeVan);
    }
}