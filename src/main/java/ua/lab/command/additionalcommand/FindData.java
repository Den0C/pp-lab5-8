package ua.lab.command.additionalcommand;

import ua.lab.coffeeCar.CoffeeVan;

import java.util.Scanner;

public class FindData {
    private Scanner scanner = new Scanner(System.in);

    public void findByFourElm(CoffeeVan coffeeVan) {
        System.out.print("\nВведіть тип кави: ");
        String type = scanner.nextLine();
        System.out.print("\nВведіть вид кави: ");
        String kind = scanner.nextLine();
        System.out.print("\nВведіть рік виготовлення кави: ");
        Integer year = scanner.nextInt();
        System.out.print("\nВведіть мінімальну ціну кави: ");
        double minCost = scanner.nextDouble();
        System.out.print("\nВведіть максимальну ціну кави: ");
        double maxCost = scanner.nextDouble();

        coffeeVan.findByDiapason(type, kind, year, minCost, maxCost);
    }

    public void findByTwoElm1(CoffeeVan coffeeVan) {
        System.out.print("\nВведіть мінімальну ціну кави: ");
        double minCost = scanner.nextDouble();
        System.out.print("\nВведіть максимальну ціну кави: ");
        double maxCost = scanner.nextDouble();

        coffeeVan.findByDiapason(minCost, maxCost);
    }

    public void findByOneElm(CoffeeVan coffeeVan) {
        System.out.print("\nВведіть тип кави: ");
        String type = scanner.nextLine();
        coffeeVan.findByDiapason(type);
    }

    public void findByTwoElm2(CoffeeVan coffeeVan) {
        System.out.print("\nВведіть тип кави: ");
        String type = scanner.nextLine();
        System.out.print("\nВведіть вагу кави: ");
        Double weight = scanner.nextDouble();
        coffeeVan.findByDiapason(type,weight);
    }

}