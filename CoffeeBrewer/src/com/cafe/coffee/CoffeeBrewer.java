package com.cafe.coffee;

public class CoffeeBrewer implements CoffeeService {
    @Override
    public void makeCoffee(String customerName) {
        System.out.println("Coffe Brewer is making coffee for " + customerName);
    }
}
