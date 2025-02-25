package com.cafe.juice;

import com.cafe.api.JuiceOrderListener;

public class JuiceOrderScreen implements JuiceOrderListener {

    @Override
    public void onJuiceOrder(String customerName) {
        // Logic for handling the juice order
        System.out.println("🍹 Juice order received for " + customerName + ". Enjoy your juice!");
    }
}
