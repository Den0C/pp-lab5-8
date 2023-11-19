package ua.lab.good;

import java.util.Objects;

public class Coffee {
    private String type;
    private String kind;
    private Double cost;
    private Integer yearOfManufacture;

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Coffee(String type, String kind, double cost, Integer yearOfManufacture) {
        this.type = type;
        this.kind = kind;
        this.cost = cost;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(type, coffee.type) && Objects.equals(kind, coffee.kind) && Objects.equals(cost, coffee.cost) && Objects.equals(yearOfManufacture, coffee.yearOfManufacture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, kind, cost, yearOfManufacture);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", cost=" + cost +
                ", yearOfManufacture=" + yearOfManufacture +
                '}';
    }
}
