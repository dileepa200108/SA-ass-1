package com.cafe.coffeeorderscreen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.cafe.api.CoffeeOrderListener;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        // Register CoffeeOrderListener service
        System.out.println("✅ Coffee Order Screen - Ready to Display Coffee Orders");
        
        CoffeeOrderListener coffeeOrderScreen = new CoffeeOrderScreen();
        context.registerService(CoffeeOrderListener.class, coffeeOrderScreen, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the CoffeeOrderListener service if necessary
    }
}
