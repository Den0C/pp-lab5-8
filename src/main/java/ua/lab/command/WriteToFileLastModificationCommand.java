package ua.lab.command;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.file.FileSetterAndGetter;

import java.util.Optional;

public class WriteToFileLastModificationCommand implements Command {
    private FileSetterAndGetter fileSetterAndGetter;
    public WriteToFileLastModificationCommand(FileSetterAndGetter fileSetterAndGetter){
        this.fileSetterAndGetter = fileSetterAndGetter;
    }

    @Override
    public String getInfo() {
        return "записати останні змінни у файл (write-data-to-file)";
    }

    @Override
    public void execute(CoffeeVan coffeeVan, Optional<String> commands) {
        coffeeVan.writeToFileLastModification(fileSetterAndGetter);
    }
}