package ua.lab.adminpanel;
import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.command.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminPanel {
    private final CoffeeVan coffeeVan;
    private final Map<String, Command> adminCommands;

    public AdminPanel(CoffeeVan coffeeVan, Map<String, Command> adminCommands) {
        this.coffeeVan = coffeeVan;
        this.adminCommands = adminCommands;
    }

    public void getAndApplyCommand(String consoleCommand) {
        List<String> commands = parseConsoleCommand(consoleCommand);
        adminCommands.get(getGeneralCommand(commands)).execute(coffeeVan, getAdditionalCommands(commands));
    }

    public void printCommand(){
        adminCommands.forEach((key, value) -> System.out.println(value.getInfo()));
    }

    private Optional<String> getAdditionalCommands(List<String> commands) {
        if (commands.size() > 1)
            return Optional.of(commands.get(1));
        return Optional.empty();
    }

    private String getGeneralCommand(List<String> commands) {
        return commands.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ви ввеели пусту стрічку"));
    }

    private List<String> parseConsoleCommand(String consoleCommand) {
        return Arrays.stream(consoleCommand.split(" ")).toList();
    }
}
