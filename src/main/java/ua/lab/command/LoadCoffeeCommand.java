package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.good.CoffeeContainer;
import ua.lab.util.SetCoffeeContainer;

import java.util.Optional;

public class LoadCoffeeCommand implements Command{
    private Optional<CoffeeContainer> coffeeContainer = Optional.empty();

    public LoadCoffeeCommand(CoffeeContainer coffeeContainer) {
        this.coffeeContainer = Optional.of(coffeeContainer);
    }
    public LoadCoffeeCommand(){}
    @Override
    public String getInfo() {
        return "додати каву до вагону (load-coffee)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> commands) {
        coffeeVan.loadCoffee(coffeeContainer.orElseGet(SetCoffeeContainer::createCoffeeContainer));
    }
    public Optional<CoffeeContainer> getCoffeeContainer() {
        return coffeeContainer;
    }
}
