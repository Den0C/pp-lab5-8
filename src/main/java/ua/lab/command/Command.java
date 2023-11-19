package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;

import java.util.Optional;

public interface Command {
    String getInfo();
    void execute(CoffeeVan coffeeVan, Optional<String> commands);
}
