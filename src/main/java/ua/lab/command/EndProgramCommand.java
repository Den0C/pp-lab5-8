package ua.lab.command;

import org.apache.log4j.Logger;
import ua.lab.coffeeCar.CoffeeVan;

import java.util.Optional;


public class EndProgramCommand implements Command{

    @Override
    public String getInfo() {
        return "закінчення програми (exit)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> commands) {
        Logger logger = Logger.getLogger(EndProgramCommand.class);
        logger.info("завершення програми");
        System.exit(0);
    }
}
