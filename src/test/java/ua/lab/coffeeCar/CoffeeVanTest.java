package ua.lab.coffeeCar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lab.good.Coffee;
import ua.lab.good.CoffeeContainer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeVanTest {

    private CoffeeVan coffeeVan;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        coffeeVan = new CoffeeVan(300.0, "TestVan");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    void GoodLoadCoffee() {
        List<CoffeeContainer> coffeeContainer = new ArrayList<>();
        coffeeContainer.add(new CoffeeContainer("jar", 150.0, new Coffee("arabica", "cereal", 30.0, 100)));
        coffeeContainer.add(new CoffeeContainer("jar", 50.0, new Coffee("arabica", "cereal", 10.5, 100)));
        coffeeContainer.add(new CoffeeContainer("jar", 20.0, new Coffee("arabica", "cereal", 5.5, 100)));

        coffeeVan.loadCoffee(coffeeContainer.get(0));
        coffeeVan.loadCoffee(coffeeContainer.get(1));
        coffeeVan.loadCoffee(coffeeContainer.get(2));

        List<CoffeeContainer> loadedCoffeeContainers = coffeeVan.getCoffeesContainers();

        // Перевірна на розмір
        assertEquals(coffeeContainer.size(), loadedCoffeeContainers.size());

        // Перевірка на вагу
        assertEquals(220.0, coffeeVan.getCurrentWeight());

        // Перевірка чи співпадають
        for (int i = 0; i < coffeeContainer.size(); i++)
            assertEquals(coffeeContainer.get(i), loadedCoffeeContainers.get(i));
    }

    @Test
    void exceedsTheWeight() {
        List<CoffeeContainer> coffeeContainer = new ArrayList<>();
        coffeeContainer.add(new CoffeeContainer("jar", 150.0, new Coffee("arabica", "cereal", 30.0, 100)));
        coffeeContainer.add(new CoffeeContainer("jar", 50.0, new Coffee("arabica", "cereal", 10.5, 100)));
        coffeeContainer.add(new CoffeeContainer("jar", 110.0, new Coffee("arabica", "cereal", 5.5, 100)));

        coffeeVan.loadCoffee(coffeeContainer.get(0));
        coffeeVan.loadCoffee(coffeeContainer.get(1));
        coffeeVan.loadCoffee(coffeeContainer.get(2));

        List<CoffeeContainer> loadedCoffeeContainers = coffeeVan.getCoffeesContainers();

        // Перевірна на розмір
        assertEquals(2, loadedCoffeeContainers.size());

        // Перевірка на вагу
        // 200 томущо третій елемент не має додатися
        assertEquals(200.0, coffeeVan.getCurrentWeight());

        // Перевірка чи співпадають
        //мінус одиниця томущо третій елемент не має додатися
        for (int i = 0; i < coffeeContainer.size() - 1; i++)
            assertEquals(coffeeContainer.get(i), loadedCoffeeContainers.get(i));
    }

    @Test
    void sortByCostAndWeight() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 150.0, new Coffee("arabica", "cereal", 150.0, 100));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("jar", 50.0, new Coffee("arabica", "cereal", 10.0, 100));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 100));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.sortByCostAndWeight();
        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getResult();

        assertEquals(3, resultCoffeeContainers.size());

        assertEquals(coffeeContainer2, resultCoffeeContainers.get(0));
        assertEquals(coffeeContainer3, resultCoffeeContainers.get(1));
        assertEquals(coffeeContainer1, resultCoffeeContainers.get(2));
    }

    @Test
    void findByDiapasonWithFiveParameters() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("smallBag", 50.0, new Coffee("robusta", "cereal", 40.0, 2005));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 2011));
        CoffeeContainer coffeeContainer4 = new CoffeeContainer("bigBag", 10.0, new Coffee("liberica", "ground", 20.0, 2006));
        CoffeeContainer coffeeContainer5 = new CoffeeContainer("jar", 15.0, new Coffee("arabica", "soluble", 10.0, 2009));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.loadCoffee(coffeeContainer4);
        coffeeVan.loadCoffee(coffeeContainer5);

        coffeeVan.findByDiapason("arabica", "cereal", 2000, 10.0, 150.0);
        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getResult();

        assertEquals(2, resultCoffeeContainers.size());

        assertEquals(coffeeContainer1, resultCoffeeContainers.get(0));
        assertEquals(coffeeContainer3, resultCoffeeContainers.get(1));
    }

    @Test
    void testFindByDiapasonByCost() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("smallBag", 50.0, new Coffee("robusta", "cereal", 40.0, 2005));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 2011));
        CoffeeContainer coffeeContainer4 = new CoffeeContainer("bigBag", 10.0, new Coffee("liberica", "ground", 20.0, 2006));
        CoffeeContainer coffeeContainer5 = new CoffeeContainer("jar", 15.0, new Coffee("arabica", "soluble", 10.0, 2009));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.loadCoffee(coffeeContainer4);
        coffeeVan.loadCoffee(coffeeContainer5);

        coffeeVan.findByDiapason(30.0, 150.0);
        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getResult();

        assertEquals(3, resultCoffeeContainers.size());

        assertEquals(coffeeContainer1, resultCoffeeContainers.get(0));
        assertEquals(coffeeContainer2, resultCoffeeContainers.get(1));
        assertEquals(coffeeContainer3, resultCoffeeContainers.get(2));
    }

    @Test
    void testFindByDiapasonByTypeCoffee() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("smallBag", 50.0, new Coffee("robusta", "cereal", 40.0, 2005));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 2011));
        CoffeeContainer coffeeContainer4 = new CoffeeContainer("bigBag", 10.0, new Coffee("liberica", "ground", 20.0, 2006));
        CoffeeContainer coffeeContainer5 = new CoffeeContainer("jar", 15.0, new Coffee("arabica", "soluble", 10.0, 2009));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.loadCoffee(coffeeContainer4);
        coffeeVan.loadCoffee(coffeeContainer5);

        coffeeVan.findByDiapason("arabica");
        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getResult();

        assertEquals(3, resultCoffeeContainers.size());

        assertEquals(coffeeContainer1, resultCoffeeContainers.get(0));
        assertEquals(coffeeContainer3, resultCoffeeContainers.get(1));
        assertEquals(coffeeContainer5, resultCoffeeContainers.get(2));
    }

    @Test
    void testFindByDiapasonByTypeOfContainerAndWeight() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("smallBag", 50.0, new Coffee("robusta", "cereal", 40.0, 2005));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 2011));
        CoffeeContainer coffeeContainer4 = new CoffeeContainer("bigBag", 10.0, new Coffee("liberica", "ground", 20.0, 2006));
        CoffeeContainer coffeeContainer5 = new CoffeeContainer("jar", 15.0, new Coffee("arabica", "soluble", 10.0, 2009));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.loadCoffee(coffeeContainer4);
        coffeeVan.loadCoffee(coffeeContainer5);

        coffeeVan.findByDiapason("jar", 70.0);
        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getResult();

        assertEquals(2, resultCoffeeContainers.size());

        assertEquals(coffeeContainer1, resultCoffeeContainers.get(0));
        assertEquals(coffeeContainer3, resultCoffeeContainers.get(1));
    }

    @Test
    void deleteCoffee() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000));
        CoffeeContainer coffeeContainer2 = new CoffeeContainer("smallBag", 50.0, new Coffee("robusta", "cereal", 40.0, 2005));
        CoffeeContainer coffeeContainer3 = new CoffeeContainer("jar", 70.0, new Coffee("arabica", "cereal", 30.0, 2011));
        CoffeeContainer coffeeContainer4 = new CoffeeContainer("bigBag", 10.0, new Coffee("liberica", "ground", 20.0, 2006));
        CoffeeContainer coffeeContainer5 = new CoffeeContainer("jar", 15.0, new Coffee("arabica", "soluble", 10.0, 2009));

        coffeeVan.loadCoffee(coffeeContainer1);
        coffeeVan.loadCoffee(coffeeContainer2);
        coffeeVan.loadCoffee(coffeeContainer3);
        coffeeVan.loadCoffee(coffeeContainer4);
        coffeeVan.loadCoffee(coffeeContainer5);

        List<CoffeeContainer> resultCoffeeContainers = coffeeVan.getCoffeesContainers();

        assertEquals(5, resultCoffeeContainers.size());

        // Перевірка при видаленні присутнього елемента
        coffeeVan.deleteCoffee(new CoffeeContainer("jar", 100.0, new Coffee("arabica", "cereal", 150.0, 2000)));
        assertEquals(4, resultCoffeeContainers.size());

        //Перевірка при видаленні елемента який не наявний
        coffeeVan.deleteCoffee(new CoffeeContainer("jar", 101.0, new Coffee("arabica", "cereal", 150.0, 2000)));
        assertEquals(4, resultCoffeeContainers.size());

    }

    @Test
    void testToString() {
        CoffeeContainer coffeeContainer1 = new CoffeeContainer("jar", 150.0, new Coffee("arabica", "cereal", 150.0, 100));
        coffeeVan.loadCoffee(coffeeContainer1);

        String expectedToString = "CoffeeVan{maxWeightCoffee=300.0, name='TestVan', coffeesContainers=[CoffeeContainer{typeContainer='jar', weight=150.0, coffee=Coffee{type='arabica', kind='cereal', cost=150.0, yearOfManufacture=100}}]}";
        assertEquals(expectedToString, coffeeVan.toString());

    }
}