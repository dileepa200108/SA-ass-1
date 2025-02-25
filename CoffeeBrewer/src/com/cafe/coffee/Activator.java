package com.cafe.coffee;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.CoffeeService;

public class Activator implements BundleActivator {

    private ServiceRegistration<CoffeeService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        CoffeeService coffeeService = new CoffeeBrewer();
        registration = context.registerService(CoffeeService.class, coffeeService, null);
        System.out.println("✅ Coffee Service Registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println("❌ Coffee Service Unregistered");
    }
}
