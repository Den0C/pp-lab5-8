package ua.lab.file;

import ua.lab.coffeeCar.CoffeeVan;
import ua.lab.command.LoadCoffeeCommand;
import ua.lab.good.Coffee;
import ua.lab.good.CoffeeContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileSetterAndGetter {

    public CoffeeVan getDataFromFIle() {
        String fileName = "D:\\javaPrograming\\LabsPP\\lab4-5\\data.txt";
        CoffeeVan coffeeVan = null;

        List<CoffeeContainer> coffeeContainers = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (coffeeVan == null) {
                    coffeeVan = new CoffeeVan(Double.parseDouble(line), bufferedReader.readLine());
                    continue;
                }

                coffeeContainers.add(new CoffeeContainer(line,
                        Double.parseDouble(bufferedReader.readLine()),
                        new Coffee(bufferedReader.readLine()
                                , bufferedReader.readLine()
                                , Double.parseDouble(bufferedReader.readLine())
                                , Integer.parseInt(bufferedReader.readLine()))));
            }

            LoadCoffeeCommand loadCoffee;
            for(CoffeeContainer coffeeContainer : coffeeContainers) {
                loadCoffee = new LoadCoffeeCommand(coffeeContainer);
                loadCoffee.execute(coffeeVan, Optional.empty());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coffeeVan;
    }

    public void writeToFile(List<CoffeeContainer> coffeeContainers){
        String fileName = "D:\\javaPrograming\\LabsPP\\lab4-5\\result.txt";

        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int num = 1;
            for(CoffeeContainer coffeeContainer: coffeeContainers){
                bufferedWriter.write("№"+ num++ +"\nТип ємності: " + coffeeContainer.getTypeContainer());
                bufferedWriter.write("\nВага:" + coffeeContainer.getWeight());
                bufferedWriter.write("\nТип кави: " + coffeeContainer.getCoffee().getType());
                bufferedWriter.write("\nВид: " + coffeeContainer.getCoffee().getKind());
                bufferedWriter.write("\nЦіна: " + coffeeContainer.getCoffee().getCost());
                bufferedWriter.write("\nРік: " + coffeeContainer.getCoffee().getYearOfManufacture()+"\n\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
