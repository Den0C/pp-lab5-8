package ua.lab.good;

import java.util.Objects;

public class CoffeeContainer {
    private String typeContainer;
    private Double weight;
    private Coffee coffee;

    public CoffeeContainer(String type, Double weight, Coffee coffee) {
        this.typeContainer = type;
        this.weight = weight;
        this.coffee = coffee;
    }

    public String getTypeContainer() {
        return typeContainer;
    }

    public void setTypeContainer(String typeContainer) {
        this.typeContainer = typeContainer;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeContainer that = (CoffeeContainer) o;
        return Objects.equals(typeContainer, that.typeContainer) && Objects.equals(weight, that.weight) && Objects.equals(coffee, that.coffee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeContainer, weight, coffee);
    }

    @Override
    public String toString() {
        return "CoffeeContainer{" +
                "typeContainer='" + typeContainer + '\'' +
                ", weight=" + weight +
                ", coffee=" + coffee +
                '}';
    }
}
