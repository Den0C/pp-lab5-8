package ua.lab.util;

import ua.lab.good.Coffee;
import ua.lab.good.CoffeeContainer;

import java.util.Scanner;

public class SetCoffeeContainer {

    public static CoffeeContainer createCoffeeContainer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть тип ємності: ");
        String type = scanner.nextLine();
        System.out.print("\nВведіть вагу: ");
        Double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("\nВведіть тип кави: ");
        String typeCoffee = scanner.nextLine();
        System.out.print("\nВведіть вид кави: ");
        String kind = scanner.nextLine();
        System.out.print("\nВведіть ціну кави: ");
        double cost = scanner.nextDouble();
        System.out.print("\nВведіть рік виготовлення кави: ");
        Integer year = scanner.nextInt();

        return new CoffeeContainer(type,weight,new Coffee(typeCoffee,kind,cost,year));
    }
}
