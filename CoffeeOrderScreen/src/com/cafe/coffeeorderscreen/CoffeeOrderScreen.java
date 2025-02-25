package com.cafe.coffeeorderscreen;

import com.cafe.api.CoffeeOrderListener;

public class CoffeeOrderScreen implements CoffeeOrderListener {

    @Override
    public void onCoffeeOrder(String customerName) {
        // Display the coffee order message
        System.out.println("☕ Coffe Screen - order received for " + customerName + ". Enjoy your coffee!");
    }
}
