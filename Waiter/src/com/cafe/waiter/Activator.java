package com.cafe.waiter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import com.cafe.coffee.CoffeeService;
import com.cafe.juice.JuiceService;
import java.util.Scanner;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Ask for customer name
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        // Ask for drink choice
        System.out.print("What would you like to drink (Coffee or Juice)? ");
        String orderChoice = scanner.nextLine();

        // Forward the request to the correct producer (Coffee or Juice)
        if ("Coffee".equalsIgnoreCase(orderChoice)) {
            // Get the CoffeeService and process the order
            ServiceReference<CoffeeService> coffeeRef = context.getServiceReference(CoffeeService.class);
            if (coffeeRef != null) {
                CoffeeService coffeeService = context.getService(coffeeRef);
                coffeeService.makeCoffee(customerName);
                System.out.println("Order for " + customerName + " is ready: Coffee");

                // Start the TextConsumer (Coffee Consumer)
                startConsumerBundle(context, "com.cafe.coffeeconsumer");
            }
        } else if ("Juice".equalsIgnoreCase(orderChoice)) {
            // Get the JuiceService and process the order
            ServiceReference<JuiceService> juiceRef = context.getServiceReference(JuiceService.class);
            if (juiceRef != null) {
                JuiceService juiceService = context.getService(juiceRef);
                juiceService.makeJuice(customerName);
                System.out.println("Order for " + customerName + " is ready: Juice");

                // Start the AudioConsumer (Juice Consumer)
                startConsumerBundle(context, "com.cafe.juiceconsumer");
            }
        } else {
            System.out.println("Invalid order choice. Please choose either Coffee or Juice.");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Waiter service stopped");
    }

    // Method to start the correct consumer bundle dynamically based on the drink type
    private void startConsumerBundle(BundleContext context, String consumerBundleName) {
        Bundle[] bundles = context.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals(consumerBundleName)) {
                try {
                    bundle.start();
                    System.out.println("Starting " + consumerBundleName + " bundle...");
                } catch (Exception e) {
                    System.out.println("Failed to start " + consumerBundleName + " bundle: " + e.getMessage());
                }
            }
        }
    }
}
