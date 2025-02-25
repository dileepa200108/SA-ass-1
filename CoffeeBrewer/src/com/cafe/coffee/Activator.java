package com.cafe.coffee;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        CoffeeService coffeeService = new CoffeeBrewer();
        serviceRegistration = context.registerService(CoffeeService.class, coffeeService, null);
        System.out.println("Coffee Service Registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Coffee Service Unregistered");
    }
}
