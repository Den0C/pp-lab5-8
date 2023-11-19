package ua.lab.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lab.coffeeCar.CoffeeVan;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrintDataCommandTest {
    private PrintDataCommand printDataCommand;
    private CoffeeVan coffeeVan;

    @BeforeEach
    void setUp() {
        printDataCommand = new PrintDataCommand();
        coffeeVan = mock(CoffeeVan.class);
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "вивести на екран (print-data downloaded/processed)";
        String info = printDataCommand.getInfo();
        assertEquals(expectedInfo, info);
    }

    @Test
    void testExecuteWithDownloaded() {
        Optional<String> additionalCommand = Optional.of("downloaded");
        printDataCommand.execute(coffeeVan, additionalCommand);
        verify(coffeeVan).printData("downloaded");
        verifyNoMoreInteractions(coffeeVan);
    }

    @Test
    void testExecuteWithProcessed() {
        Optional<String> additionalCommand = Optional.of("processed");
        printDataCommand.execute(coffeeVan, additionalCommand);
        verify(coffeeVan).printData("processed");
        verifyNoMoreInteractions(coffeeVan);
    }

    @Test
    void testExecuteWithNoAdditionalCommand() {
        Optional<String> additionalCommand = Optional.empty();
        printDataCommand.execute(coffeeVan, additionalCommand);
        verifyNoInteractions(coffeeVan);
    }
}