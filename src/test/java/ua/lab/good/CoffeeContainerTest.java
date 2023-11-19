package ua.lab.good;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeContainerTest {
    @Test
    void testConstructorAndGetters() {
        Coffee coffee = new Coffee("Espresso", "Arabica", 5.0, 2023);
        CoffeeContainer container = new CoffeeContainer("Bag", 1.0, coffee);

        assertEquals("Bag", container.getTypeContainer());
        assertEquals(1.0, container.getWeight());
        assertEquals(coffee, container.getCoffee());
    }

    @Test
    void testSetters() {
        Coffee coffee1 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        CoffeeContainer container = new CoffeeContainer("Bag", 1.0, coffee1);

        Coffee coffee2 = new Coffee("Cappuccino", "Robusta", 4.5, 2022);
        container.setTypeContainer("Box");
        container.setWeight(2.0);
        container.setCoffee(coffee2);

        assertEquals("Box", container.getTypeContainer());
        assertEquals(2.0, container.getWeight());
        assertEquals(coffee2, container.getCoffee());
    }

    @Test
    void testEquals() {
        Coffee coffee1 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        CoffeeContainer container1 = new CoffeeContainer("Bag", 1.0, coffee1);
        CoffeeContainer container2 = new CoffeeContainer("Bag", 1.0, coffee1);
        CoffeeContainer container3 = new CoffeeContainer("Box", 2.0, coffee1);
        assertTrue(container1.equals(container2));
        assertFalse(container1.equals(container3));
    }

    @Test
    void testHashCode() {
        Coffee coffee1 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        CoffeeContainer container1 = new CoffeeContainer("Bag", 1.0, coffee1);
        CoffeeContainer container2 = new CoffeeContainer("Bag", 1.0, coffee1);
        assertEquals(container1.hashCode(), container2.hashCode());
    }

    @Test
    void testToString() {
        Coffee coffee = new Coffee("Espresso", "Arabica", 5.0, 2023);
        CoffeeContainer container = new CoffeeContainer("Bag", 1.0, coffee);
        assertEquals("CoffeeContainer{typeContainer='Bag', weight=1.0, coffee=Coffee{type='Espresso', kind='Arabica', cost=5.0, yearOfManufacture=2023}}", container.toString());
    }

}