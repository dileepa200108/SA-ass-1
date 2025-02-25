package com.cafe.waiter;

import com.cafe.api.CoffeeOrderListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class Waiter implements BundleActivator {

    private ServiceReference<CoffeeOrderListener> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Waiter is ready to take orders...");
        
        // Get the service reference for CoffeeOrderListener
        serviceReference = context.getServiceReference(CoffeeOrderListener.class);
        
        if (serviceReference != null) {
            // Get the service instance
            CoffeeOrderListener listener = context.getService(serviceReference);
            if (listener != null) {
                // Simulate interaction: Taking order from user
                Scanner scanner = new Scanner(System.in);
                
                System.out.print("👤 Enter your name: ");
                String customerName = scanner.nextLine();
                
                System.out.print("☕🍹 What would you like to drink (Coffee or Juice)? ");
                String drinkChoice = scanner.nextLine();
                
                if (drinkChoice.equalsIgnoreCase("Coffee")) {
                    System.out.println("☕ Coffee Brewer is making coffee for " + customerName);
                    
                    // Trigger the CoffeeOrderListener to notify about the coffee order
                    listener.onCoffeeOrder(customerName);
                    
                    System.out.println("✅ Waiter - Order for " + customerName + " is ready: Coffee");
                }
                // You can add similar functionality for Juice here if needed
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister services if needed (optional)
    }
}
