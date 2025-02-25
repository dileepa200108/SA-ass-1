package com.cafe.juice;

import com.cafe.api.JuiceOrderListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Juice Service Registered");

        // Register the JuiceOrderScreen as a JuiceOrderListener service
        JuiceOrderListener juiceOrderListener = new JuiceOrderScreen();
        context.registerService(JuiceOrderListener.class, juiceOrderListener, null);

        System.out.println("✅ Juice Order Screen - Ready to Display Juice Orders");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Juice Order Screen stopped.");
    }
}
