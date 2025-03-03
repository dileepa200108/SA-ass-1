package com.cafe.waiter;

import com.cafe.api.CoffeeOrderListener;
import com.cafe.api.CupCakeOrderListener;
import com.cafe.api.JuiceOrderListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Activator implements BundleActivator {

    private ServiceReference<CoffeeOrderListener> coffeeServiceReference;
    private ServiceReference<JuiceOrderListener> juiceServiceReference;
    private ServiceReference<CupCakeOrderListener>cupCakeServiceReference;
    
    
    //available cupcake flavors
    private static final List<String> AVAILABLE_FLAVORS = Arrays.asList("vanilla", "chocolate", "strawberry");

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Waiter is ready to take orders...");

        // Get the service reference for CoffeeOrderListener
        coffeeServiceReference = context.getServiceReference(CoffeeOrderListener.class);

        // Get the service reference for JuiceOrderListener
        juiceServiceReference = context.getServiceReference(JuiceOrderListener.class);
        
        //get the service reference for CupCakeOrderListner
        cupCakeServiceReference = context.getServiceReference(CupCakeOrderListener.class);

        if (coffeeServiceReference != null && juiceServiceReference != null && cupCakeServiceReference != null) {
            // Get the service instances (CoffeeOrderListener, JuiceOrderListener)
            CoffeeOrderListener coffeeListener = context.getService(coffeeServiceReference);
            JuiceOrderListener juiceListener = context.getService(juiceServiceReference);
            CupCakeOrderListener cupCakeListener = context.getService(cupCakeServiceReference);

            if (coffeeListener != null && juiceListener != null && cupCakeListener != null) {
                // Simulate interaction: Taking order from user
                Scanner scanner = new Scanner(System.in);

                System.out.print("👤 Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.print("☕🍹 What would you like to drink (Coffee or Juice or CupCake)? ");
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
                }else if(drinkChoice.equalsIgnoreCase("CupCake")){
                	
                	String flavor = "";
                    boolean validFlavor = false;
                	
                    while (!validFlavor) {
                        System.out.print("🧁 What flavor would you like for your cupcake? (vanilla, chocolate, or strawberry): ");
                        flavor = scanner.nextLine().toLowerCase();
                        
                        if (AVAILABLE_FLAVORS.contains(flavor)) {
                            validFlavor = true;
                        } else {
                            System.out.println("❌ Sorry, we only offer vanilla, chocolate, and strawberry cupcakes.");
                        }
                    }
                	
                    System.out.println("🧁 CupCake Baker is making a " + flavor + " cupcake for " + customerName);
                	 cupCakeListener.onCupCakeOrder(customerName,flavor);
                	 System.out.println("✅ Waiter - Order for " + customerName + " is ready: " + flavor + " CupCake");
                }else {
                	 System.out.println("❌ Sorry, we don't offer that item. Please choose Coffee, Juice, or CupCake.");
                }
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Waiter Stopped Working.");
    }
}
