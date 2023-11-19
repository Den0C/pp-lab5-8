package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.command.additionalcommand.AdditioanlCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrintDataCommand implements Command {
    @Override
    public String getInfo() {
        return "вивести на екран (print-data downloaded/processed)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> additionalCommand) {
        additionalCommand.ifPresent(command -> getCommands(coffeeVan).get(command).execute());
    }

    private Map<String, AdditioanlCommand> getCommands(CoffeeVan coffeeVan) {
        Map<String, AdditioanlCommand> commands = new HashMap<>();
        commands.put("downloaded", () -> coffeeVan.printData("downloaded"));
        commands.put("processed", () -> coffeeVan.printData("processed"));
        return commands;
    }
}