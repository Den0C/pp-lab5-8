package ua.lab.coffeeCar;


import org.apache.log4j.Logger;
import ua.lab.file.FileSetterAndGetter;
import ua.lab.good.CoffeeContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoffeeVan {
    private double maxWeightCoffee;
    private double currentWeight;
    private String name;

    private static  final Logger logger = Logger.getLogger(CoffeeVan.class);
    private List<CoffeeContainer> coffeesContainers = new ArrayList<>();
    private List<CoffeeContainer> result = new ArrayList<>();

    public CoffeeVan() {
    }

    public CoffeeVan(Double maxWeightCoffee, String name) {
        this.maxWeightCoffee = maxWeightCoffee;
        this.name = name;
    }

    public Double getMaxWeightCoffee() {
        return maxWeightCoffee;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public String getName() {
        return name;
    }

    public List<CoffeeContainer> getCoffeesContainers() {
        return coffeesContainers;
    }

    public List<CoffeeContainer> getResult() {
        return result;
    }

    public void setMaxWeightCoffee(Double maxWeightCoffee) {
        this.maxWeightCoffee = maxWeightCoffee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void loadCoffee(CoffeeContainer coffeeContainer) {
        if (suitable(coffeeContainer)) {
            return;
        }
        logger.info("завантажили каву");
        currentWeight += coffeeContainer.getWeight();
        coffeesContainers.add(coffeeContainer);
    }

    private boolean suitable(CoffeeContainer coffeeContainer) {
        return coffeeContainer.getWeight() + currentWeight > maxWeightCoffee;
    }

    public void sortByCostAndWeight() {
        result = coffeesContainers.stream().sorted((c1, c2) -> {
            double num1 = c1.getCoffee().getCost() / c1.getWeight();
            double num2 = c2.getCoffee().getCost() / c2.getWeight();

            return Double.compare(num1, num2);
        }).collect(Collectors.toList());
        logger.info("посортували каву за ціною і вагою");
    }

    public void findByDiapason(String type, String kind, Integer yearOfM, Double minCost, Double maxCost) {
        result = coffeesContainers.stream()
                .filter(x -> x.getCoffee().getType().equals(type) && x.getCoffee().getKind().equals(kind)
                        && x.getCoffee().getYearOfManufacture() >= yearOfM
                        && x.getCoffee().getCost() >= minCost && x.getCoffee().getCost() <= maxCost)
                .collect(Collectors.toList());

        logger.info("знайшли каву за типом, видом, почтковим роком, мін та макс ціною");
    }

    public void findByDiapason(Double minCost, Double maxCost) {
        result = coffeesContainers.stream()
                .filter(x -> x.getCoffee().getCost() >= minCost && x.getCoffee().getCost() <= maxCost)
                .collect(Collectors.toList());

        logger.info("занайшли каву за мін та макс ціною");
    }

    public void findByDiapason(String type) {
        result = coffeesContainers.stream()
                .filter(x -> x.getCoffee().getType().equals(type))
                .collect(Collectors.toList());
        logger.info("занайшли каву за типом кави");
    }

    public void findByDiapason(String type, Double weight) {
        result = coffeesContainers.stream()
                .filter(x -> x.getTypeContainer().equals(type) && x.getWeight() >= weight)
                .collect(Collectors.toList());
        logger.info("знайшли каву за типом контейнера та вагою");
    }

    public void deleteCoffee(CoffeeContainer coffeeContainer) {
        coffeesContainers.remove(coffeeContainer);
        logger.info("видалили каву");
    }

    public void writeToFileLastModification(FileSetterAndGetter fileSetterAndGetter) {
        fileSetterAndGetter.writeToFile(result);
        logger.info("зааписали оброблену дані у файл");
    }

    public void printData(String s) {
        if (s.equals("downloaded"))
            coffeesContainers.forEach(System.out::println);
        else if(s.equals("processed"))
            result.forEach(System.out::println);
        logger.info("надрукували дані на екран");
    }

    @Override
    public String toString() {
        return "CoffeeVan{" +
                "maxWeightCoffee=" + maxWeightCoffee +
                ", name='" + name + '\'' +
                ", coffeesContainers=" + coffeesContainers +
                '}';
    }

    public List<CoffeeContainer> getCoffeeContainers() {
        return this.coffeesContainers;
    }
}