package com.cafe.juice;

public class JuiceMaker implements JuiceService {
    @Override
    public void makeJuice(String customerName) {
        System.out.println("Juice maker is making juice for " + customerName);
    }
}
