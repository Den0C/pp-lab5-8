package ua.lab.good;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {
    @Test
    void testConstructorAndGetters() {
        Coffee coffee = new Coffee("Espresso", "Arabica", 5.0, 2023);

        assertEquals("Espresso", coffee.getType());
        assertEquals("Arabica", coffee.getKind());
        assertEquals(5.0, coffee.getCost());
        assertEquals(2023, coffee.getYearOfManufacture());
    }

    @Test
    void testSetters() {
        Coffee coffee = new Coffee("Espresso", "Arabica", 5.0, 2023);

        coffee.setType("Cappuccino");
        coffee.setKind("Robusta");
        coffee.setCost(4.5);
        coffee.setYearOfManufacture(2022);

        assertEquals("Cappuccino", coffee.getType());
        assertEquals("Robusta", coffee.getKind());
        assertEquals(4.5, coffee.getCost());
        assertEquals(2022, coffee.getYearOfManufacture());
    }

    @Test
    void testEquals() {

        Coffee coffee1 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        Coffee coffee2 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        Coffee coffee3 = new Coffee("Cappuccino", "Robusta", 4.5, 2022);

        assertEquals(coffee1, coffee2);
        assertNotEquals(coffee1, coffee3);
    }

    @Test
    void testHashCode() {
        Coffee coffee1 = new Coffee("Espresso", "Arabica", 5.0, 2023);
        Coffee coffee2 = new Coffee("Espresso", "Arabica", 5.0, 2023);

        assertEquals(coffee1.hashCode(), coffee2.hashCode());
    }

    @Test
    void testToString() {
       Coffee coffee = new Coffee("Espresso", "Arabica", 5.0, 2023);

       assertEquals("Coffee{type='Espresso', kind='Arabica', cost=5.0, yearOfManufacture=2023}", coffee.toString());
    }

}