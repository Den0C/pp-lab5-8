package ua.lab.menu;

import org.apache.log4j.Logger;
import ua.lab.adminpanel.AdminPanel;
import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.command.*;
import ua.lab.file.FileSetterAndGetter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
   private static  final Logger logger = Logger.getLogger(Menu.class);
    public void start() {
        logger.info("програма запустилася");

        FileSetterAndGetter fileSetterAndGetter = new FileSetterAndGetter();

        Scanner scanner = new Scanner(System.in);
        CoffeeVan coffeeVan = createCoffeeVan(fileSetterAndGetter);

        Map<String, Command> commands = new HashMap<>();
        commands.put("exit",new EndProgramCommand());
        commands.put("load-coffee", new LoadCoffeeCommand());
        commands.put("delete-coffee", new DeleteCoffeeCommand());
        commands.put("sort-data", new SortByCostAndWeightCommand());
        commands.put("find-data", new FindByDiapasonCommand());
        commands.put("print-data", new PrintDataCommand());
        commands.put("write-data-to-file", new WriteToFileLastModificationCommand(fileSetterAndGetter));


        AdminPanel adminPanel = new AdminPanel(coffeeVan, commands);
        adminPanel.printCommand();
        while (true) {
            logger.info("вибір команди");
            try {
                adminPanel.getAndApplyCommand(scanner.nextLine());
            }
            catch (Exception e){
                logger.error("не вірна команда");
                System.out.println("Ви ввели не вірну команду");
            }
        }

    }
    private static CoffeeVan createCoffeeVan( FileSetterAndGetter fileSetterAndGetter){
        Scanner scanner = new Scanner(System.in);
        CoffeeVan coffeeVan = new CoffeeVan();
        System.out.println("Ввести дані з файлу? (yes or not)");

        if(scanner.nextLine().equals("yes")) {
            logger.info("вибрався режим вводу");
            logger.info("ввели дані з файлу");
            return fileSetterAndGetter.getDataFromFIle();
        }
        else {
            System.out.println("Введіть ім'я автомобіля");
            coffeeVan.setName(scanner.nextLine());
            System.out.println("Введіть ємність автомобіля");
            coffeeVan.setMaxWeightCoffee(scanner.nextDouble());
        }

        logger.info("вибрався режим вводу");
        return coffeeVan;
    }
}