package com.cafe.waiter;

import com.cafe.api.CoffeeOrderListener;
import com.cafe.api.JuiceOrderListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class Activator implements BundleActivator {

    private ServiceReference<CoffeeOrderListener> coffeeServiceReference;
    private ServiceReference<JuiceOrderListener> juiceServiceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Waiter is ready to take orders...");

        // Get the service reference for CoffeeOrderListener
        coffeeServiceReference = context.getServiceReference(CoffeeOrderListener.class);

        // Get the service reference for JuiceOrderListener
        juiceServiceReference = context.getServiceReference(JuiceOrderListener.class);

        if (coffeeServiceReference != null && juiceServiceReference != null) {
            // Get the service instances (CoffeeOrderListener, JuiceOrderListener)
            CoffeeOrderListener coffeeListener = context.getService(coffeeServiceReference);
            JuiceOrderListener juiceListener = context.getService(juiceServiceReference);

            if (coffeeListener != null && juiceListener != null) {
                // Simulate interaction: Taking order from user
                Scanner scanner = new Scanner(System.in);

                System.out.print("👤 Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.print("☕🍹 What would you like to drink (Coffee or Juice)? ");
                String drinkChoice = scanner.nextLine();

                if (drinkChoice.equalsIgnoreCase("Coffee")) {
                    System.out.println("☕ Coffee Brewer is making coffee for " + customerName);

                    // Trigger the CoffeeOrderListener to notify about the coffee order
                    coffeeListener.onCoffeeOrder(customerName);

                    System.out.println("✅ Waiter - Order for " + customerName + " is ready: Coffee");
                } else if (drinkChoice.equalsIgnoreCase("Juice")) {
                    System.out.println("🍹 Juice Maker is making juice for " + customerName);

                    // Trigger the JuiceOrderListener to notify about the juice order
                    juiceListener.onJuiceOrder(customerName);

                    System.out.println("✅ Waiter - Order for " + customerName + " is ready: Juice");
                }
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Waiter Stopped Working.");
    }
}
