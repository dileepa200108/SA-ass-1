package com.cafe.juice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        JuiceService juiceService = new JuiceMaker();
        serviceRegistration = context.registerService(JuiceService.class, juiceService, null);
        System.out.println("Juice Service Registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Juice Service Unregistered");
    }
}
