package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;

import java.util.Optional;

public class SortByCostAndWeightCommand implements Command{

    @Override
    public String getInfo() {
        return "посортувати (sort-data)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> commands) {
        coffeeVan.sortByCostAndWeight();
    }
}
