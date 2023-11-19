package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.good.CoffeeContainer;
import ua.lab.util.SetCoffeeContainer;

import java.util.Optional;

public class DeleteCoffeeCommand implements Command {
    private final Optional<CoffeeContainer> coffeeContainer = Optional.empty();

    public DeleteCoffeeCommand() {
    }

    @Override
    public String getInfo() {
        return "видалити каву (delete-coffee)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> commands) {
        coffeeVan.deleteCoffee(coffeeContainer.orElseGet(SetCoffeeContainer::createCoffeeContainer));
    }
}