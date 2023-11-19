package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.command.additionalcommand.AdditioanlCommand;
import ua.lab.command.additionalcommand.FindData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindByDiapasonCommand implements Command {


    @Override
    public String getInfo() {
        return "знайти товар (find-data by-type-kind-year-min-max/by-min-max/by-type/by-typecontainer-weight)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> additionalCommand) {
        additionalCommand.ifPresent(command -> createCommands(coffeeVan).get(command).execute());
    }

    private Map<String, AdditioanlCommand> createCommands(CoffeeVan coffeeVan) {
        FindData findData = new FindData();

        Map<String, AdditioanlCommand> commands = new HashMap<>();

        commands.put("by-type-kind-year-min-max", () -> findData.findByFourElm(coffeeVan));
        commands.put("by-min-max", () -> findData.findByTwoElm1(coffeeVan));
        commands.put("by-type", () -> findData.findByOneElm(coffeeVan));
        commands.put("by-typecontainer-weight", () -> findData.findByTwoElm2(coffeeVan));


        return commands;

    }
}
