package com.cafe.coffeeconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.coffee.CoffeeService;

public class CoffeeOrderListener implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        // Register the CoffeeService listener to display orders
        ServiceReference<CoffeeService> coffeeRef = context.getServiceReference(CoffeeService.class);
        if (coffeeRef != null) {
            CoffeeService coffeeService = context.getService(coffeeRef);
            coffeeService.makeCoffee("Jane Doe");
            System.out.println("Displaying Coffee Order on Screen: Coffee for Jane Doe");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Coffee Consumer service stopped");
    }
}
